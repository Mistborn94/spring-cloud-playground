package com.example.cloudstream.demo

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
class MessageListener(val testProperties: TestProperties) {

    private val logger = LoggerFactory.getLogger(MessageListener::class.java)

    @StreamListener(Sink.INPUT)
    fun incomingMessage(messageHolder: String) {
        logger.info("Config Value: {}" , testProperties.refresh)
        logger.info("Consuming message {}", messageHolder)
    }
}