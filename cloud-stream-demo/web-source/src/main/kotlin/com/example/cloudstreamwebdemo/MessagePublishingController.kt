package com.example.cloudstreamwebdemo

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessagePublishingController(val messagePublisher: MessagePublisher) {

    @PostMapping("publish")
    fun sendMessage(@RequestBody message: String) {
        messagePublisher.publishMessage(message)
    }
}