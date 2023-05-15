package com.fcfsevent.scheduler

import com.fcfsevent.singleton.Singleton
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class RankScheduler {

    @Scheduled(cron = "0 * * * * *")
    fun resetRank() {
        Singleton.atomicInteger.set(0)
    }
}
