package com.fcfsevent.webhook

import com.fcfsevent.util.http.HttpUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DiscordWebhookService(
    @Value("\${WEBHOOK_URL}")
    private val url: String,
    private val http: HttpUtils
) {
    fun postRequest(content: String) {
        postRequest(DiscordWebhookDto(content = content))
    }

    fun postRequest(body: DiscordWebhookDto) {
        http.post(url, body)
    }
}
