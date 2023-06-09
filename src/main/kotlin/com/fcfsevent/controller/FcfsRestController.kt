package com.fcfsevent.controller

import com.fcfsevent.dto.PostRankDto
import com.fcfsevent.dto.ResultDto
import com.fcfsevent.service.FcfsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class FcfsRestController(
    val fcfsService: FcfsService
) {
    @GetMapping("/ranks")
    fun getRank(): ResultDto {
        return fcfsService.getRank()
    }

    @PostMapping("/ranks")
    fun postRank(@RequestBody postRankDto: PostRankDto): ResultDto {
        return fcfsService.postRank(postRankDto.name)
    }
}
