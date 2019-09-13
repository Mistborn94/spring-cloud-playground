package com.example.cloudstream.demo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

// Adding @RefreshScope onto the message listener class broke stuff
// So now we are using @ConfigurationProperties
@Component
@ConfigurationProperties("example.test")
class TestProperties {
    lateinit var refresh: String
}