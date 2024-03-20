package com.bibekananda.routes


import com.bibekananda.data.model.ApiResponse
import com.bibekananda.data.model.endpoint.Endpoint
import com.bibekananda.domain.TaskRepository
import com.bibekananda.util.Constants.SUB_ID
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.fetchTask(taskRepository: TaskRepository, app: Application) {
    authenticate("jwt-auth") {
        get(Endpoint.FetchTasks.path) {
            val principal = call.authentication.principal<JWTPrincipal>()
            try {
                val subId = principal?.payload?.getClaim(SUB_ID)?.asString()
                if (subId != null) {
                    val tasks = taskRepository.getTask(subId = subId)
                    call.respond(
                        message = ApiResponse(
                            success = true,
                            response = tasks,
                            message = "Fetch Successfully",
                        ), status = HttpStatusCode.OK
                    )
                } else {
                    call.respond(message = ApiResponse<String>(success = false, message = "Some Thing Went Wrong"),status = HttpStatusCode.BadRequest)
                }
            } catch (e: Exception) {
                call.respond(
                    message = ApiResponse<String>(success = false, message = "Something Went Wrong"),
                    status = HttpStatusCode.Unauthorized
                )
            }
        }
    }
}