package ru.whalekit.ktpushservice.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.whalekit.ktpushservice.shared.Game
import ru.whalekit.ktpushservice.shared.Platform

@Configuration
@ConfigurationProperties(prefix = "push-service")
class PushServiceConfiguration {
    lateinit var game: Game
    lateinit var platform: Platform
}