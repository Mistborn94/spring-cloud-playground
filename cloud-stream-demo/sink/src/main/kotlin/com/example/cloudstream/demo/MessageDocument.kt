package com.example.cloudstream.demo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Document("messages")
data class MessageDocument(private val content: String,
                      private val timestamp: Instant) {

    @Id
    lateinit var id: String

    override fun toString(): String {
        return "MessageDocument(id='$id', content='$content', timestamp=${FORMATTER.format(timestamp)})"
    }

    companion object {
        private val FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault())
    }
}