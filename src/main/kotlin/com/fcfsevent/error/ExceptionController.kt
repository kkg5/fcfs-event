package com.fcfsevent.error

import com.fcfsevent.webhook.DiscordWebhookService
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController(
    private val webhook: DiscordWebhookService
) {
    @ExceptionHandler
    fun handle(e: Exception): ErrorResult {
        webhook.error(e)
        return ErrorResult()
    }
}
