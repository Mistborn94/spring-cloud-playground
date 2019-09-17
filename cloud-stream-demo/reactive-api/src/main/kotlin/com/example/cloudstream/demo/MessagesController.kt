package com.example.cloudstream.demo

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class MessagesController(val reactiveMessageRepository: ReactiveMessageRepository) {

    private val logger = LoggerFactory.getLogger(MessagesController::class.java)

    @GetMapping("/messages")
    fun getAllMessages(): Flux<MessageDocument> {
        return reactiveMessageRepository.findAll()
    }

    @GetMapping("/message/{id}")
    fun getMessageById(@PathVariable("id") id: String): Mono<MessageDocument> {
        return reactiveMessageRepository.findById(id)
                .doOnNext{ logger.info("Fetched Item: {}", it) }
    }
}