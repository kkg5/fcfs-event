package com.fcfsevent.service

import com.fcfsevent.singleton.Singleton
import org.springframework.stereotype.Service

@Service
class FcfsService {
    fun getRank(): Int {
        return Singleton.atomicInteger.addAndGet(1)
    }
}
