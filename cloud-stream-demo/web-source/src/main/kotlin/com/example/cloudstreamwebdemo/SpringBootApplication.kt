package com.example.cloudstreamwebdemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SourceDemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(SourceDemoApplication::class.java, *args)
}