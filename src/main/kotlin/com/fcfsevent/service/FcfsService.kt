package com.fcfsevent.service

import com.fcfsevent.dto.ResultDto
import com.fcfsevent.singleton.Singleton.atomicInteger
import com.fcfsevent.singleton.Singleton.map
import com.fcfsevent.util.getIp
import org.springframework.stereotype.Service

@Service
class FcfsService {
    fun getRank(): ResultDto {
        val ip = getIp()
        var rank = 0

        if (ip != null && map.contains(ip)) {
            rank = map[ip]!!
        }

        return ResultDto(rank, map.map { it.key.substring(0, 7) to it.value }.toMap())
    }

    fun postRank(): ResultDto {
        val ip = getIp()
        var rank = 0

        if (ip != null && !map.containsKey(ip)) {
            rank = atomicInteger.addAndGet(1)
            map[ip] = rank
        }

        return ResultDto(
            map[ip] ?: rank,
            map.map { it.key.substring(0, 7) to it.value }.toMap()
        )
    }
}
