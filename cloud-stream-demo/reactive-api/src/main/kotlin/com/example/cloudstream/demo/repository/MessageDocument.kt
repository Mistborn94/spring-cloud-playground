package com.example.cloudstream.demo.repository

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("messages")
data class MessageDocument constructor(@Id val id: String,
                                       val content: String,
                                        //Setting only the pattern causes failures. Setting only the timezone does not work as expected.
                                        // An alternative option is using LocalDateTime since it is in the system default timezone.
                                        // LocalDateTime works okay for display purposes, but does not show the timezone
                                       @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "CAT") val timestamp: Instant)