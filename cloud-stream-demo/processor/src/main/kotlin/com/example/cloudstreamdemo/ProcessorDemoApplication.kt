package com.example.cloudstreamdemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime

@SpringBootApplication
@EnableBinding(Processor::class)
class ProcessorDemoApplication {

    @Bean
    fun addTimestamp(): (String) -> MessageHolder {
        return { message ->
            println("Processing $message")
            MessageHolder(message, LocalDateTime.now()) }
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
    SpringApplication.run(ProcessorDemoApplication::class.java, *args)
}