package com.example.cloudstream.demo

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.function.Consumer

@Service
@EnableBinding(Sink::class)
class MessageListener {

    val registry = MyListenerRegistry<String>()
    val logger = getKLogger(MessageListener::class)

    private var source: Flux<String> = Flux.create<String> { sink ->
        //The outer lambda is a sink consumer
        registry.register { event ->
            //The inner lambda is the event listener.
            // Any time the registry.next is called this consumer is triggered and it pushes data to the sink.
            sink.next(event)
        }
    }

    fun getEvents(): Flux<String> {
        return source.doOnNext { logger.info("Emit $it") }
    }

    @StreamListener(Sink.INPUT)
    fun listen(value: String) {
        registry.next(value)
    }
}

class MyListenerRegistry<T> {
    private val listeners = mutableListOf<Consumer<T>>()

    fun register(listener: (T) -> Unit) {
        listeners.add(Consumer(listener))
    }

    fun next(data: T) {
        listeners.forEach { it.accept(data) }
    }
}