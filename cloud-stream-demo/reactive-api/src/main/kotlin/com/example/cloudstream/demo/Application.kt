package com.example.cloudstream.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ReactiveWebApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReactiveWebApiApplication::class.java, *args)
}