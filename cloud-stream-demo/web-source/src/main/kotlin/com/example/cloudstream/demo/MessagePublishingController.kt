package com.example.cloudstream.demo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class MessagePublishingController(val messagePublisher: MessagePublisher,
                                  val testProperties: TestProperties) {

    @PostMapping("publish")
    @ResponseStatus(HttpStatus.CREATED)
    fun sendMessage(@RequestBody message: String) {
        messagePublisher.publishMessage(message)
    }

    @GetMapping("config")
    fun getProperty(): String {
        return testProperties.refresh
    }
}