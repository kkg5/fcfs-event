package com.fcfsevent.singleton

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

object Singleton {
    val atomicInteger: AtomicInteger = AtomicInteger()
    val rankMap = ConcurrentHashMap<String, Int>()
    val nameMap = ConcurrentHashMap<String, String>()
    val mapper = jacksonObjectMapper()
}
