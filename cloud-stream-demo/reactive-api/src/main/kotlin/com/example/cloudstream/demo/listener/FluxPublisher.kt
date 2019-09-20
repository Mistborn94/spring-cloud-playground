package com.example.cloudstream.demo.listener

import reactor.core.publisher.FluxSink
import java.util.function.Consumer

/**
 * The publisher allows publishing items from an imperative context to a Flux
 *
 * Example:
 * ```
 *   val publisher = FluxPublisher<String>()
 *   val source: Flux<String> = Flux.create<String>(publisher.fluxSinkListener())
 *   ...
 *   publisher.publish(value)
 * ```
 */
class FluxPublisher<T> {
    private val consumers = mutableListOf<Consumer<T>>()

    private fun addConsumer(listener: (T) -> Unit) {
        consumers.add(Consumer(listener))
    }

    /**
     * Constructs a consumer that can be used to construct a [reactor.core.publisher.Flux] with the [reactor.core.publisher.Flux.create] method
     *
     * The consumer publishes an item to the consumed sink every time [publish] is called
     */
    fun emitter(): Consumer<FluxSink<T>> {
        return Consumer { sink: FluxSink<T> ->
            //The outer lambda is a sink consumer. This is the only context the sink is available in
            this.addConsumer { event ->
                //The inner lambda is the event listener.
                // Any time the registry.next is called this consumer is triggered and it pushes data to the sink.
                sink.next(event)
            }
        }
    }

    fun publish(data: T) {
        consumers.forEach { it.accept(data) }
    }
}