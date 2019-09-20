package com.example.cloudstream.demo.listener

import com.example.cloudstream.demo.helpers.getKLogger
import reactor.core.publisher.FluxSink
import java.util.*
import java.util.concurrent.CopyOnWriteArraySet
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
class FluxPublisher<T> : Consumer<FluxSink<T>> {

    //We need a thread safe collection here, otherwise registering and de-registering multiple sinks at the same time can cause Concurrent Modification Exceptions
    private val sinks = CopyOnWriteArraySet<FluxSink<T>>()
    private val logger = getKLogger(FluxPublisher::class)

    /**
     * Register a [FluxSink] and publish all future emitted messages to it
     */
    override fun accept(sink: FluxSink<T>) {
        sinks.add(sink)
        logger.info("Sink {} Registered", sink.hashCode())

        sink.onDispose {
            logger.info("Sink {} Disposed", sink.hashCode())
            sinks.remove(sink)
        }
    }

    /**
     * Publish an item to every registered [FluxSink]
     * @param data The value to publish
     */
    fun publish(data: T) {
        logger.info("Publishing '{}' to {} sinks", data, sinks.size)
        sinks.forEach {
            logger.info("Publishing message to sink {}", it.hashCode())
            it.next(data)
        }
    }
}