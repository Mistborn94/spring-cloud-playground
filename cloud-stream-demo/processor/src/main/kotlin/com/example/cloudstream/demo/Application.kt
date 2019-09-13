package com.example.cloudstream.demo

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime

@SpringBootApplication
@EnableBinding(Processor::class)
@RefreshScope
class ProcessorApplication(val testProperties: TestProperties) {

    private val logger = LoggerFactory.getLogger(ProcessorApplication::class.java)

    @Bean
    fun logMessage(): (String) -> String {
        return { message ->
            logger.info("Config Value: {}", testProperties.refresh)
            logger.info("Message {}", message)
            message
        }
    }

    @Bean
    fun addTimestamp(): (String) -> MessageHolder {
        return { message ->
            MessageHolder(message, LocalDateTime.now())
        }
    }

    @Bean
    fun toUpperCase(): (String) -> String {
        return String::toUpperCase
    }

    //The usage of kotlin functions instead of java Functions seems to require this converter.
    //Weird. This needs more investigation
    @Bean
    fun byteToString(): (ByteArray) -> String {
        return ::String
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(ProcessorApplication::class.java, *args)
}
