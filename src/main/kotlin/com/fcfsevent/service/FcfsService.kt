package com.fcfsevent.service

import com.fcfsevent.dto.ResultDto
import com.fcfsevent.singleton.Singleton.atomicInteger
import com.fcfsevent.singleton.Singleton.nameMap
import com.fcfsevent.singleton.Singleton.rankMap
import com.fcfsevent.util.getIp
import com.fcfsevent.webhook.DiscordWebhookService
import org.springframework.stereotype.Service

@Service
class FcfsService(
    val webhook: DiscordWebhookService
) {
    fun getRank(): ResultDto {
        val ip = getIp()
        var rank = 0

        if (ip != null && rankMap.contains(ip)) {
            rank = rankMap[ip]!!
        }

        return ResultDto(rank, getResultMap())
    }

    fun postRank(name: String): ResultDto {
        val ip = getIp()

        if (ip != null) {
            if (!rankMap.containsKey(ip)) {
                val rank = atomicInteger.addAndGet(1)
                rankMap[ip] = rank
                webhook.postRequest("${name}님이 ${rank}등으로 누르셨습니다.\nIP: $ip")
            } else if (nameMap[ip] != name) {
                webhook.postRequest("${nameMap[ip]}님이 $name(으)로 이름을 변경하셨습니다.")
            }

            if (name.isEmpty()) {
                nameMap[ip] = ip.substring(0, 7)
            } else {
                nameMap[ip] = name
            }
        }

        return ResultDto(rankMap[ip] ?: 0, getResultMap())
    }

    private fun getResultMap(): Map<Int, String> {
        val rankMap = rankMap.toMap()
        val nameMap = nameMap.toMap()
        return rankMap.map { it.value to nameMap[it.key]!! }
            .toMap().toSortedMap()
    }
}
