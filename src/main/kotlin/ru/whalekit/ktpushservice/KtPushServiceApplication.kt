package ru.whalekit.ktpushservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KtPushServiceApplication

fun main(args: Array<String>) {
    runApplication<KtPushServiceApplication>(*args)
}
