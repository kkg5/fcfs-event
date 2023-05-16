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

        if (ip != null && !map.values.contains(ip)) {
            rank = atomicInteger.addAndGet(1)
            map[rank] = ip
        }

        return ResultDto(rank, map.map { it.key to it.value?.substring(0, 7) }.toMap())
    }
}
