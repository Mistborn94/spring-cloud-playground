package com.example.cloudstream.demo.helpers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

fun getKLogger(clazz: KClass<out Any>): Logger {
    return LoggerFactory.getLogger(clazz.java)
}