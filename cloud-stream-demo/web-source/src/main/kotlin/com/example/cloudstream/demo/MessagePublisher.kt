package com.example.cloudstream.demo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.integration.support.MessageBuilder

@EnableBinding(Source::class)
class MessagePublisher(val source: Source,
                       val testProperties: TestProperties) {

    val logger: Logger = LoggerFactory.getLogger(MessagePublisher::class.java)

    fun publishMessage(message: String) {
        logger.info("Config Value: {}" , testProperties.refresh)
        logger.info("Sending message: {}" , message)

        source.output().send(MessageBuilder.withPayload(message).build())
    }
}