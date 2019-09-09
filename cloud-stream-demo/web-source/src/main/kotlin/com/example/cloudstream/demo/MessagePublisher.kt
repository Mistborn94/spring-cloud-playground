package com.example.cloudstream.demo

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.integration.support.MessageBuilder

@EnableBinding(Source::class)
class MessagePublisher(val source: Source) {

    fun publishMessage(message: String) {
        println("Sending message: $message")

        source.output().send(MessageBuilder.withPayload(message).build())
    }
}