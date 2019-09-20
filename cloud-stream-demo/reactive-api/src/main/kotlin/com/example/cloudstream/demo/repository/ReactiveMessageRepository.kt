package com.example.cloudstream.demo.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ReactiveMessageRepository : ReactiveCrudRepository<MessageDocument, String>