package com.example.cloudstream.demo

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.context.annotation.Bean
import java.time.Instant

@SpringBootApplication
@EnableBinding(Processor::class)
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
            MessageHolder(message, Instant.now())
        }
    }

    @Bean
    fun toUpperCase(): (String) -> String {
        return String::toUpperCase
    }
    
    @Bean
    fun trim(): (String) -> String {
        return String::trim
    }

    //The usage of kotlin functions instead of java Functions seems to require this converter.
    //Changing the first function in the chain to be a java.util.function.Function<String, String> also fixes it
    @Bean
    fun byteToString(): (ByteArray) -> String {
        return ::String
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(ProcessorApplication::class.java, *args)
}
