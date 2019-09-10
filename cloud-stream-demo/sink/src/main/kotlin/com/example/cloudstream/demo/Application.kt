package com.example.cloudstream.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SinkDemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(SinkDemoApplication::class.java, *args)
}
