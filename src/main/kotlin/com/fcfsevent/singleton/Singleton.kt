package com.fcfsevent.singleton

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

object Singleton {
    val atomicInteger: AtomicInteger = AtomicInteger()
    val map = ConcurrentHashMap<Int, String?>()
}
