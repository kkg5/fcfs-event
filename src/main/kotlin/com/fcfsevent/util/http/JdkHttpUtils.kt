package com.fcfsevent.util.http

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
class JdkHttpUtils : HttpUtils {
    private val mapper = jacksonObjectMapper()

    override fun post(uri: String, body: Any) {
        val json: String = mapper.writeValueAsString(body)

        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .build()

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    }
}
