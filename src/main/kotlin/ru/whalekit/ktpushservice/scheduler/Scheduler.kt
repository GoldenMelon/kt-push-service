package ru.whalekit.ktpushservice.scheduler

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service
import ru.whalekit.ktpushservice.configuration.PushServiceConfiguration
import ru.whalekit.ktpushservice.executor.PushExecutor
import ru.whalekit.ktpushservice.push.Push
import ru.whalekit.ktpushservice.push.PushRepository
import ru.whalekit.ktpushservice.push.Status
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

@Service
class Scheduler(val pushRepository: PushRepository, val pushExecutor: PushExecutor, configuration: PushServiceConfiguration) : ApplicationRunner {
    private final val lock: Lock = ReentrantLock()
    private final val conditionEmpty: Condition = lock.newCondition()
//    private final val executorService: ExecutorService = Executors.newFixedThreadPool(10)
    private final val game = configuration.game
//    private final val platform = configuration.platform

    override fun run(args: ApplicationArguments) {
        while (!Thread.currentThread().isInterrupted) {
            lock.lock()
            try {
                val pushes = pushRepository.findByGameAndTimeLessThanAndStatus(game, LocalDateTime.now(), Status.SCHEDULED)
                if (pushes.isEmpty()) {
                    val firstPush = pushRepository.findFirstByGameAndStatus(game, Status.SCHEDULED)
                    if (firstPush != null) {
                        val until = LocalDateTime.now().until(firstPush.time, ChronoUnit.SECONDS)
                        conditionEmpty.await(until,  TimeUnit.SECONDS)
                    } else {
                        conditionEmpty.await()
                    }
                } else {
                    val map = pushes.map { pushRepository.save(it.copy(status = Status.PROCESSING)) }
                    map
                            .forEach(pushExecutor::execute)
                }
            } finally {
                lock.unlock()
            }
        }
    }

    fun schedule(push: Push) {
        lock.lock()
        try {
            pushRepository.save(push)
            conditionEmpty.signal()
        } finally {
            lock.unlock()
        }
    }

    fun unschedule(push: Push) {
        lock.lock()
        try {
            pushRepository.save(push)
            conditionEmpty.signal()
        } finally {
            lock.unlock()
        }

    }
}