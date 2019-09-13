package com.example.cloudstream.demo

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessagePublishingController(val messagePublisher: MessagePublisher,
                                  val testProperties: TestProperties) {

    @PostMapping("publish")
    fun sendMessage(@RequestBody message: String) {
        messagePublisher.publishMessage(message)
    }

    @GetMapping("config")
    fun getProperty(): String {
        return testProperties.refresh
    }
}