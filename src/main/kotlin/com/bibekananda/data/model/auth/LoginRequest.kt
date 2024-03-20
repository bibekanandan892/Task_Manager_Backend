package com.bibekananda.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String? = null ,
    val password : String? = null
)