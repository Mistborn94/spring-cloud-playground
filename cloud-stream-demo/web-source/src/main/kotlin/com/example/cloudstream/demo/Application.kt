package com.example.cloudstream.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class WebSourceApplication

fun main(args: Array<String>) {
    SpringApplication.run(WebSourceApplication::class.java, *args)
}