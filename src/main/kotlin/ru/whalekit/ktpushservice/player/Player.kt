package ru.whalekit.ktpushservice.player

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.whalekit.ktpushservice.shared.Game
import ru.whalekit.ktpushservice.shared.Platform

@Document(collection = "Player")
data class Player(
        @Id val id: String,
        val locale: String,
        val game: Game,
        val platform: Platform
)