package com.example.cloudstream.demo

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

@Document("messages")
data class MessageDocument constructor(@Id val id: String,
                                       val content: String,
                                       val timestamp: LocalDateTime) {
}