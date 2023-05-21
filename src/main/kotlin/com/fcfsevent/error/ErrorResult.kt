package com.fcfsevent.error

data class ErrorResult(
    val status: Int = 500,
    val message: String = ""
)
