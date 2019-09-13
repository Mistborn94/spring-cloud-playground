package com.example.cloudstream.demo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

// The refresh scope annotation needs to be on any bean that should be refreshed.
// Externalising the refreshable configs to a @ConfigurationProperties pojo
// removes the need to annotate every bean with @RefreshScope
@Component
@ConfigurationProperties("example.test")
class TestProperties {
    lateinit var refresh: String
}