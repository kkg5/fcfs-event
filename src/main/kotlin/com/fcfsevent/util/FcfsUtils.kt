package com.fcfsevent.util

import org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes
import org.springframework.web.context.request.ServletRequestAttributes

val headers = listOf(
    "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
    "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED",
    "X-Real-IP", "X-RealIP", "REMOTE_ADDR", "HTTP_VIA", "IPV6_ADR"
)

fun getIp(): String? {
    val request = (currentRequestAttributes() as ServletRequestAttributes).request
    var ip = request.remoteAddr
    headers.forEach {
        if (ip != null && ip.isNotEmpty() && !"unknown".equals(ip, ignoreCase = true) && ip != "127.0.0.1") {
            return ip
        }
        ip = request.getHeader(it)
    }
    return ip
}
