package com.bibekananda.routes


import com.bibekananda.data.model.ApiResponse
import com.bibekananda.data.model.UploadPhotoRequest
import com.bibekananda.data.model.endpoint.Endpoint
import com.bibekananda.data.model.task.Task
import com.bibekananda.domain.TaskRepository
import com.bibekananda.domain.UserRepository
import com.bibekananda.util.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.updateImage(userRepository: UserRepository) {
    authenticate("jwt-auth") {
        patch(Endpoint.UpdatePhoto.path) {
            val principal = call.authentication.principal<JWTPrincipal>()
            try {
                val subId = principal?.payload?.getClaim(Constants.SUB_ID)?.asString()
                val taskRequest = call.receive<UploadPhotoRequest>()
                if (subId != null && taskRequest.photo!= null) {
                    val tasks = userRepository.uploadProfile(subId = subId, photo =taskRequest.photo )

                    call.respond(
                        message = ApiResponse(
                            success = tasks.success,
                            response = null,
                            message = tasks.message,
                        ), status = if(tasks.success) HttpStatusCode.OK else HttpStatusCode.BadRequest
                    )
                } else {
                    call.respond(message = ApiResponse<String>(success = false, message = "Some Thing Went Wrong"), status = HttpStatusCode.BadRequest)
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