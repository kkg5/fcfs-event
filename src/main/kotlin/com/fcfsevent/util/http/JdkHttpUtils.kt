package com.fcfsevent.util.http

import com.fcfsevent.singleton.Singleton.mapper
import com.fcfsevent.util.logger
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
class JdkHttpUtils : HttpUtils {
    val logger = this.logger()

    override fun post(uri: String, body: Any) {
        val json: String = mapper.writeValueAsString(body)

        logger.info("Webhook JSON: $json")

        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .build()

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenAcceptAsync @Async {
                if (it.statusCode() == 400) {
                    logger.warn("Webhook Status: ${it.statusCode()}")
                    logger.warn("Webhook Body: ${it.body()}")
                    throw RuntimeException("Webhook Error: ${it.body()}")
                } else {
                    logger.info("Webhook Status: ${it.statusCode()}")
                    logger.info("Webhook Body: ${it.body()}")
                }
            }
    }
}
