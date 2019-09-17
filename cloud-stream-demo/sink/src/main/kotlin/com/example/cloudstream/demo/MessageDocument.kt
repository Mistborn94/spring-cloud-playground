package com.example.cloudstream.demo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("messages")
class MessageDocument(private val content: String,
                      private val timestamp: LocalDateTime) {

    @Id
    lateinit var id: String

    override fun toString(): String {
        return "MessageDocument(id='$id', content='$content', timestamp=$timestamp)"
    }
}