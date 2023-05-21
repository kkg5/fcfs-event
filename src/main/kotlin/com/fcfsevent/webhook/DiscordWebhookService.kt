package com.fcfsevent.webhook

import com.fcfsevent.util.getIp
import com.fcfsevent.util.getRequestURI
import com.fcfsevent.util.getStringOfParamMap
import com.fcfsevent.util.getUserAgent
import com.fcfsevent.util.http.HttpUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DiscordWebhookService(
    @Value("\${WEBHOOK_URL}")
    private val url: String,
    @Value("\${WEBHOOK_DEBUG_URL}")
    private val debugUrl: String,
    @Value("\${WEBHOOK_ERROR_URL}")
    private val errorUrl: String,
    private val http: HttpUtils
) {
    fun postContent(title: String, content: String) {
        val body = DiscordWebhookDto(
            embeds = listOf(
                Embed(
                    title = title,
                    description = content,
                    fields = listOf(
                        EmbedField(name = "IP", value = getIp().toString())
                    )
                )
            )
        )

        http.post(url, body)
    }

    fun debug() {
        val body = DiscordWebhookDto(
            embeds = listOf(
                Embed(
                    title = "Debug",
                    fields = getCommonField()
                )
            )
        )

        http.post(debugUrl, body)
    }

    fun error(e: Throwable) {
        val body = DiscordWebhookDto(
            embeds = listOf(
                Embed(
                    title = e.javaClass.canonicalName,
                    fields = getCommonField().plus(
                        listOf(
                            EmbedField(name = "Message", value = e.message.toString())
                        )
                    )
                )
            )
        )

        http.post(errorUrl, body)
    }

    private fun getCommonField(): List<EmbedField> {
        return listOf(
            EmbedField(name = "URI", value = getRequestURI()),
            EmbedField(name = "Parameter Map", value = getStringOfParamMap()),
            EmbedField(name = "IP", value = getIp().toString()),
            EmbedField(name = "User Agent", value = getUserAgent().toString())
        )
    }
}
