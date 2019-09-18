package com.example.cloudstream.demo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("messages")
data class MessageDocument constructor(@Id val id: String,
                                       val content: String,
                                       //The local date time fetches the date from the database and converts it to a
                                       //LocalDateTime in the system default timezone. It works fine for display purposes.
                                       val timestamp: LocalDateTime)