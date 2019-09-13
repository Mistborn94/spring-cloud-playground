package com.example.cloudstream.demo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.integration.support.MessageBuilder

@EnableBinding(Source::class)
class MessagePublisher(val source: Source) {

    @Value("\${example.test-refresh}")
    private lateinit var configValue: String

    val logger: Logger = LoggerFactory.getLogger(MessagePublisher::class.java)

    fun publishMessage(message: String) {
        logger.info("Config Value: {}" , configValue)
        logger.info("Sending message: {}" , message)

        source.output().send(MessageBuilder.withPayload(message).build())
    }
}