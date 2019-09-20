package com.example.cloudstream.demo.listener

import org.reactivestreams.Publisher
import reactor.core.publisher.FluxSink
import java.util.function.Consumer

/**
 * The publisher allows publishing items to a Flux from an imperative context.
 *
 * The class acts as both a Publisher ([publish] method) and a [FluxSink] consumer ([accept] method)
 *
 * Usage Example:
 * ```
 *   val publisher = FluxPublisher<String>()
 *   val source: Flux<String> = Flux.create<String>(publisher)
 *   ...
 *   publisher.publish(value)
 * ```
 */
class FluxPublisher<T>: Consumer<FluxSink<T>> {

    private val consumers = mutableListOf<Consumer<T>>()

    private fun addConsumer(listener: (T) -> Unit) {
        consumers.add(Consumer(listener))
    }

    /**
     * Register a [FluxSink] and publish all future emitted messages to it
     */
    override fun accept(sink: FluxSink<T>) {
        this.addConsumer { event ->
            //The inner lambda is the event listener.
            // Any time the registry.next is called this consumer is triggered and it pushes data to the sink.
            sink.next(event)
        }
    }

    /**
     * Publish an item to every registered [FluxSink]
     * @param data The value to publish
     */
    fun publish(data: T) {
        consumers.forEach { it.accept(data) }
    }
}