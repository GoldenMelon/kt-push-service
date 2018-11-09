package ru.whalekit.ktpushservice.executor

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.whalekit.ktpushservice.locale.Locale
import ru.whalekit.ktpushservice.locale.LocaleRepository
import ru.whalekit.ktpushservice.message.LocalizedMessageRepository
import ru.whalekit.ktpushservice.message.Message
import ru.whalekit.ktpushservice.player.Player
import ru.whalekit.ktpushservice.player.PlayerRepository
import ru.whalekit.ktpushservice.push.Push
import java.lang.IllegalStateException
import java.util.concurrent.Executors

@Service
class BasicMrgsPushExecutor(
        val playerRepository: PlayerRepository,
        val localeRepository: LocaleRepository,
        val localizedMessageRepository: LocalizedMessageRepository
) : PushExecutor {
    val pageLimit = 50000
    val executorService = Executors.newFixedThreadPool(20)

    override fun execute(push: Push) {
        val locales = localeRepository.findAll()
        val nanoTime = System.nanoTime()
        locales.forEach { locale ->
            var pageable: Pageable = PageRequest.of(0, pageLimit)
            var playerPage = playerRepository.findByGameAndPlatformAndLocale(push.game, push.platform, locale.name, pageable)
            val message = localizedMessageRepository.findById(push.messageId)
                    .orElseThrow { IllegalStateException("Could not be found message") }
                    .let { localizedMessage ->
                        localizedMessage.locales[locale.name] ?: localizedMessage.locales["en"]
                        ?: throw IllegalStateException("Can't find english locale")
                    }
            while (playerPage.hasNext()) {
                executorService.submit { sendPush(playerPage, push, locale, message) }
                pageable = playerPage.nextPageable()
                playerPage = playerRepository.findByGameAndPlatformAndLocale(push.game, push.platform, locale.name, pageable)
            }
            sendPush(playerPage, push, locale, message)
        }
        println("time to execute is ${(System.nanoTime() - nanoTime)/1000.0/1000.0}}")
    }

    fun sendPush(playerPage: Page<Player>, push: Push, locale: Locale, message: Message) {
        val nanoTime = System.nanoTime()
        val players: String = playerPage.map { player -> player.id }
                .joinToString(separator = ",")
        val mrgsPush = MrgsPush(message.title, message.message, players)
        println("${mrgsPush.title} ${(System.nanoTime() - nanoTime)/1000.0/1000.0}")
    }
}

data class MrgsPush(val title: String, val message: String, val ids: String)