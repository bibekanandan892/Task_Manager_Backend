package com.bibekananda.data.model

import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("success")
    val success: Boolean,
    @SerialName("response")

    val response: T? = null,
    @SerialName("message")

    val message: String? = null
)