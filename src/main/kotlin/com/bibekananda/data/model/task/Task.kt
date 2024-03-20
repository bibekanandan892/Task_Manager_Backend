package com.bibekananda.data.model.task

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val userId : String,
    val taskId : String,
    val taskTitle : String,
    val taskDescription : String? = null,
    val time : Long,
    val status : Int,
)
