package com.example.cloudstream.demo.listener

import com.example.cloudstream.demo.helpers.getKLogger
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
@EnableBinding(Sink::class)
class ProcessorListener {

    private val logger = getKLogger(ProcessorListener::class)

    private val publisher = FluxPublisher<String>()
    private val source: Flux<String> = Flux.create<String>(publisher)

    fun getEvents(): Flux<String> {
        return source.doOnError { e -> logger.error("Failure fetching items ", e) }
    }

    @StreamListener(Sink.INPUT)
    fun listen(value: String) {
        logger.info("Read '{}' from stream", value)
        publisher.publish(value)
    }
}

