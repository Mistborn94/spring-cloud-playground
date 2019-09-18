package com.example.cloudstream.demo

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class MessageHolder(val message: String, val timestamp: Instant) {

    override fun toString(): String {
        return "MessageHolder(message='$message', timestamp=${FORMATTER.format(timestamp)})"
    }

    companion object {
        private val FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault())
    }
}