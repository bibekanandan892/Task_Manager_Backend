package com.bibekananda.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TaskRequest(val filterType : String? = null)
