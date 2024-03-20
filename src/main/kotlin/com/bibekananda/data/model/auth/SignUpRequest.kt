package com.bibekananda.data.model.auth

import kotlinx.serialization.Serializable


@Serializable
data class SignUpRequest(val name : String? = null, val emailAddress : String? = null, val password: String? = null)
