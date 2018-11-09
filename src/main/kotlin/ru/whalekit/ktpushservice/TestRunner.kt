package ru.whalekit.ktpushservice

import org.bson.types.ObjectId
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import ru.whalekit.ktpushservice.locale.Locale
import ru.whalekit.ktpushservice.locale.LocaleRepository
import ru.whalekit.ktpushservice.message.LocalizedMessageRepository
import ru.whalekit.ktpushservice.player.Player
import ru.whalekit.ktpushservice.player.PlayerRepository
import ru.whalekit.ktpushservice.push.Push
import ru.whalekit.ktpushservice.push.PushRepository
import ru.whalekit.ktpushservice.shared.Game
import ru.whalekit.ktpushservice.shared.Platform
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class TestRunner(val rep: PlayerRepository, val rep2: LocalizedMessageRepository, val rep3: PushRepository, val rep4: LocaleRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
//        val a = Push("5be31d972f6a0b33f4264164", LocalDateTime.now(), Game.ZBS, "5be31d972f6a0b33f4264111")
//        rep3.save(a)

//        println(findByGame)

//        val map = mapOf("ru" to Message("Privet", "Medved"), "en" to Message("Hello", "Bear"))
//        val a = LocalizedMessage("5be31d972f6a0b33f4264111", map)
//        rep2.save(a)
//        val a = Player(locale = "ru", game = "zbs", platform = Platform.ANDROID, id = "5be31d972f6a0b33f4264111")
//        rep.save(a)
//        val strings = listOf("zh-Hans", "zh-Hant", "en", "fr", "de", "it", "ja", "ko", "pt", "ru", "es", "th")
//        strings.map { s -> Locale(ObjectId().toHexString(), s) }
//                .forEach { rep4.save(it) }
//        for (i in 1..10_000_000) {
//            val random = Random()
//            val nextInt = random.nextInt(3)
//            val nextLocale = random.nextInt(strings.size)
//            val game = Game.values()[nextInt]
//            val a = Player(locale = strings[nextLocale], game = Game.ZBS, platform = Platform.ANDROID, id = ObjectId().toHexString())
//            rep.save(a)
//        }

//        var pageable: Pageable = PageRequest.of(0, 500)
//        var playerPage = rep.findByGameAndPlatformAndLocale(Game.ZBS, Platform.ANDROID, "en", pageable)
//        playerPage.forEach{  println(it) }

    }
}