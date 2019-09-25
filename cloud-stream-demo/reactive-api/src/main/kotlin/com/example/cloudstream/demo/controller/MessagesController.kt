package com.example.cloudstream.demo.controller

import com.example.cloudstream.demo.repository.MessageDocument
import com.example.cloudstream.demo.listener.ProcessorListener
import com.example.cloudstream.demo.repository.ReactiveMessageRepository
import com.example.cloudstream.demo.helpers.getKLogger
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class MessagesController(val repository: ReactiveMessageRepository,
                         val processorListener: ProcessorListener) {

    private val logger = getKLogger(MessagesController::class)

    @GetMapping("/message/{id}")
    fun getMessageById(@PathVariable("id") id: String): Mono<MessageDocument> {
        return repository.findById(id)
                .doOnNext { logger.info("Fetched Item: {}", it) }
                .doFinally { logger.info("Stream Exhausted") }
    }

    @GetMapping("/messages/processed")
    fun getAllMessages(): Flux<MessageDocument> {
        logger.info("Starting Fetch All Items")
        return repository.findAll()
                .doFinally { logger.info("Fetched all Items") }
                .doOnError { e -> logger.error("Failure fetching items ", e) }
    }

    @GetMapping("/messages/unprocessed", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getUnprocessedMessages(): Flux<String> {
        return processorListener.getEvents()
                .doOnNext { logger.info("\tPushing message to client: {}", it) }
    }
}