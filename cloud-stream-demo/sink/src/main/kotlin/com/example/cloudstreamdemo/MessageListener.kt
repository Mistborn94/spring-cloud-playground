package com.example.cloudstreamdemo

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
class MessageListener {

    @StreamListener(Sink.INPUT)
    fun incomingMessage(messageHolder: String) {
        println("Consuming message : $messageHolder")
    }
}