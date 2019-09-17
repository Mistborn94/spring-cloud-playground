package com.example.cloudstream.demo

import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<MessageDocument, String>