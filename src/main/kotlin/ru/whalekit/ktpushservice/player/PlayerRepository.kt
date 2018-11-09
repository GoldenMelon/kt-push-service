package ru.whalekit.ktpushservice.player

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.whalekit.ktpushservice.shared.Game
import ru.whalekit.ktpushservice.shared.Platform

@Repository
interface PlayerRepository : MongoRepository<Player, String> {
    fun findByGameAndPlatformAndLocale(game: Game, platform: Platform, locale: String, pageable: Pageable) : Page<Player>
}