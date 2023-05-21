package com.fcfsevent.webhook

import java.time.LocalDateTime

data class DiscordWebhookDto(
    val content: String = "",
    val embeds: List<Embed> = listOf()
)

data class Embed(
    val title: String? = null,
    val type: String? = null,
    val description: String? = null,
    val url: String? = "https://fcfs.duckdns.org",
    val timestamp: String? = LocalDateTime.now().toString(),
    val color: String? = null,
    val footer: EmbedFooter = EmbedFooter(),
    val fields: List<EmbedField>? = null
)

data class EmbedFooter(
    val text: String = "KKG",
    private val iconUrl: String = "https://avatars.githubusercontent.com/u/97646802?v=4",
    private val proxyIconUrl: String? = null
) {
    fun getIcon_url(): String {
        return iconUrl
    }
    fun getProxy_icon_url(): String? {
        return proxyIconUrl
    }
}

data class EmbedField(
    val name: String = "",
    val value: String = ""
)
