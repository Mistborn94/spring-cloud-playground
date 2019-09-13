package com.example.cloudstream.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.context.config.annotation.RefreshScope

@SpringBootApplication
class WebSourceApplication

fun main(args: Array<String>) {
    SpringApplication.run(WebSourceApplication::class.java, *args)
}