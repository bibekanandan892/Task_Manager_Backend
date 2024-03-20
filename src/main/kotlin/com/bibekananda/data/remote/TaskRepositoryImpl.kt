package com.bibekananda.data.remote

import com.bibekananda.data.model.Status
import com.bibekananda.data.model.task.Task
import com.bibekananda.domain.TaskRepository
import com.mongodb.client.model.Updates.combine
import org.litote.kmongo.SetTo
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setTo
import org.litote.kmongo.set
import org.litote.kmongo.setValue
class TaskRepositoryImpl(private val dataBase: CoroutineDatabase,) : TaskRepository {

    private val tasks = dataBase.getCollection<Task>()//collection is a data holder for multiple fields
    override suspend fun getTask(subId: String): List<Task> {
        val taskList =
            tasks.find(filter = Task::userId eq subId ).toList()//if I pass this user which I want to save I want to get existing user from database
        return taskList
    }

    override suspend fun insertTask(subId: String, task: Task?): Status {
        return if(task != null){
            val insert = tasks.insertOne(task).wasAcknowledged()
            return if(insert){
                Status(true,"Task Insert Successfully")
            }else{
                Status(false,"Insert Task Failed")
            }
        }else{
            Status(false,"Task is empty")
        }


    }

    override suspend fun updateTask(subId: String, task: Task?): Status {
        return if(task != null){
            val filter = Task::taskId eq task.taskId
            val update = combine(
                set( SetTo(property = Task::taskTitle,task.taskTitle)),
                set(SetTo(property = Task::taskDescription, task.taskDescription)),
                set( SetTo(Task::status , value = task.status))
                )
            val result = tasks.updateOne(filter, update).wasAcknowledged()
            return if(result){
                Status(true,"Update Successful")
            }else{
                Status(false,"Update Failed")
            }
        }else{
            Status(false,"Task is empty")
        }

    }

    override suspend fun deleteTask(subId: String, taskId: String): Status {
        val result = tasks.deleteOne(Task::taskId eq taskId).wasAcknowledged()
        return if(result){
            Status(true,"Deleted")
        }else{
            Status(false,"Failed")
        }
    }


}