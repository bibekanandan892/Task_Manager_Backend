package com.bibekananda

import com.bibekananda.domain.TaskRepository
import com.bibekananda.domain.UserRepository
import com.bibekananda.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val userRepository by inject<UserRepository>()
    val taskRepository by inject<TaskRepository>()
    configureKoin()
    configureSerialization()
    configureAuth()
    configureRouting(userRepository = userRepository, taskRepository = taskRepository)
}
