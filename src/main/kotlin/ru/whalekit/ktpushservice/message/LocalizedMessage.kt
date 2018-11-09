package ru.whalekit.ktpushservice.message

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "LocalizedMessage")
data class LocalizedMessage(@Id val id: String, val locales: Map<String, Message>)
data class Message(val title: String, val message: String)