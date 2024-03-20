package com.bibekananda.plugins

import com.bibekananda.data.model.ApiResponse
import com.bibekananda.domain.TaskRepository
import com.bibekananda.domain.UserRepository
import com.bibekananda.routes.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(userRepository: UserRepository, taskRepository: TaskRepository) {
    routing {
        rootRoute()
        signIn(app = application, userRepository = userRepository)
        signUp(app = application,userRepository = userRepository)
        getUser(userRepository = userRepository)
        updateImage(userRepository = userRepository)
        authorizedRoute()
        unauthorizedRoute()
        insertTask(taskRepository = taskRepository, app =application )
        deleteTask(taskRepository = taskRepository,application)
        updateTask(taskRepository = taskRepository,app = application)
        fetchTask(taskRepository = taskRepository, app = application)
    }
}
