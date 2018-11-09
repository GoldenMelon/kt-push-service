package ru.whalekit.ktpushservice.message

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LocalizedMessageRepository : MongoRepository<LocalizedMessage, String>