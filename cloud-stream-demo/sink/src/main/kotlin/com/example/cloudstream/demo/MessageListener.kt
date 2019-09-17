package com.example.cloudstream.demo

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
class MessageListener(val testProperties: TestProperties, val messageRepository: MessageRepository) {

    private val logger = LoggerFactory.getLogger(MessageListener::class.java)

    @StreamListener(Sink.INPUT)
    fun incomingMessage(messageHolder: MessageHolder) {
        logger.info("Config Value: {}" , testProperties.refresh)
        logger.info("Consuming message {}", messageHolder)

        val save = messageRepository.save(MessageDocument(messageHolder.message, messageHolder.timestamp))

        logger.info("Saved value {}", save)
    }
}