package com.example.cloudstream.demo

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@RestController
class MessagesController(val repository: ReactiveMessageRepository, val listener: MessageListener) {

    private val logger = getKLogger(MessagesController::class)

    @GetMapping("/messages")
    fun getAllMessages(): Flux<MessageDocument> {
        return repository.findAll()
                .doOnNext { logger.info("Fetched Item: {}", it) }
                .doFinally { logger.info("Stream Exhausted") }
    }

    @GetMapping("/message/{id}")
    fun getMessageById(@PathVariable("id") id: String): Mono<MessageDocument> {
        return repository.findById(id)
                .doOnNext { logger.info("Fetched Item: {}", it) }
                .doFinally { logger.info("Stream Exhausted") }
    }

    private val flux by lazy {
        Flux.just("a", "b", "c", "d")
                .delayElements(Duration.ofSeconds(5))
                .publish()
    }

    @GetMapping("/messages/unprocessed", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getUnprocessedMessages(): Flux<String> {
        return listener.getEvents()
    }
}