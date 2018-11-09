package ru.whalekit.ktpushservice.locale

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Locale")
data class Locale(@Id val id: String, val name: String)