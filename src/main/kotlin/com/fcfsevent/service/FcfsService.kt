package com.fcfsevent.service

import com.fcfsevent.dto.ResultDto
import com.fcfsevent.singleton.Singleton.atomicInteger
import com.fcfsevent.singleton.Singleton.map
import com.fcfsevent.util.getIp
import com.fcfsevent.webhook.DiscordWebhookDto
import com.fcfsevent.webhook.DiscordWebhookService
import org.springframework.stereotype.Service

@Service
class FcfsService(
    val webhook: DiscordWebhookService
) {
    fun getRank(): ResultDto {
        val ip = getIp()
        var rank = 0

        if (ip != null && map.contains(ip)) {
            rank = map[ip]!!
        }

        return ResultDto(rank, getResultMap())
    }

    fun postRank(): ResultDto {
        val ip = getIp()
        var rank = 0

        if (ip != null && !map.containsKey(ip)) {
            rank = atomicInteger.addAndGet(1)
            map[ip] = rank
            webhook.postRequest(
                DiscordWebhookDto(
                    "${ip}님이 ${rank}등으로 누르셨습니다."
                )
            )
        }

        return ResultDto(map[ip] ?: rank, getResultMap())
    }

    private fun getResultMap(): Map<Int, String> {
        return map.map { it.value to it.key.substring(0, 7) }
            .toMap().toSortedMap()
    }
}
