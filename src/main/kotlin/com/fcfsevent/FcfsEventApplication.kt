package com.fcfsevent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class FcfsEventApplication

fun main(args: Array<String>) {
    runApplication<FcfsEventApplication>(*args)
}
