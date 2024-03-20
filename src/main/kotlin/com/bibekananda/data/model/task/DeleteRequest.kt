package com.bibekananda.data.model.task

import kotlinx.serialization.Serializable

@Serializable
data class DeleteRequest(
    val taskId : String?= null
)
