package ru.whalekit.ktpushservice.locale

import org.springframework.data.mongodb.repository.MongoRepository

interface LocaleRepository : MongoRepository<Locale, String>