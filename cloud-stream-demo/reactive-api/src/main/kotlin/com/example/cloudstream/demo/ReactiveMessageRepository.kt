package com.example.cloudstream.demo

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ReactiveMessageRepository : ReactiveCrudRepository<MessageDocument, String>