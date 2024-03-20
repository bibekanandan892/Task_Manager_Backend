package com.bibekananda.domain

import com.bibekananda.data.model.Status
import com.bibekananda.data.model.task.Task


interface TaskRepository {

    suspend fun getTask(subId :String): List<Task>
    suspend fun insertTask(subId: String,task: Task?): Status
    suspend fun updateTask(subId: String,task: Task?): Status
    suspend fun deleteTask(subId: String,taskId: String): Status
}