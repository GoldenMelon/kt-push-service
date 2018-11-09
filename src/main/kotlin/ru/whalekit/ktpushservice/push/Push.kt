package ru.whalekit.ktpushservice.push

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.whalekit.ktpushservice.shared.Game
import ru.whalekit.ktpushservice.shared.Platform
import java.time.LocalDateTime

@Document("Push")
data class Push(
        @Id val id: String,
        val time: LocalDateTime,
        val game: Game,
        val messageId: String,
        val status: Status,
        val platform: Platform
)

enum class Status {
    SCHEDULED, PROCESSING, DELIVERED, FAILED
}