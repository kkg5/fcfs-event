package com.fcfsevent.util

import com.fcfsevent.util.http.HttpUtils
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes
import org.springframework.web.context.request.ServletRequestAttributes

fun <T : HttpUtils> T.logger(): Logger = LoggerFactory.getLogger(this::class.java)

val headers = listOf(
    "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
    "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED",
    "X-Real-IP", "X-RealIP", "REMOTE_ADDR", "HTTP_VIA", "IPV6_ADR"
)

fun getIp(): String? {
    val request = getRequest()
    var ip = request.remoteAddr
    headers.forEach {
        if (ip != null && ip.isNotEmpty() && !"unknown".equals(ip, ignoreCase = true) && ip != "127.0.0.1") {
            return ip
        }
        ip = request.getHeader(it)
    }
    return ip
}

fun getRequestURI(): String {
    return getRequest().requestURI
}

fun getStringOfParamMap(): String {
    return getRequest().parameterMap.map { "$it.key=$it.value" }.joinToString(", ")
}

fun getUserAgent(): String? {
    return getRequest().getHeader("User-Agent")
}

private fun getRequest(): HttpServletRequest {
    return (currentRequestAttributes() as ServletRequestAttributes).request
}
