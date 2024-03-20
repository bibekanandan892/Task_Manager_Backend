package com.bibekananda.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UploadPhotoRequest(
    val photo : String? = null
)
