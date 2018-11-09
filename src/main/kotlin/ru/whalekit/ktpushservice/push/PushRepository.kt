package ru.whalekit.ktpushservice.push

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import ru.whalekit.ktpushservice.shared.Game
import java.time.LocalDateTime

@Repository
interface PushRepository : MongoRepository<Push, String> {
    //    @Query(sort = "{ time : 1 }")
//    fun findByGame(game: Game): List<Push>
    @Query(sort = "{ time : 1 }")
    fun findByGameAndTimeLessThanAndStatus(zbs: Game, time: LocalDateTime, status: Status) : List<Push>
    @Query(sort = "{ time : 1 }")
    fun findFirstByGameAndStatus(zbs: Game, status: Status) : Push?
}