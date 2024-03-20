package com.bibekananda.routes

import com.bibekananda.data.model.ApiResponse
import com.bibekananda.data.model.endpoint.Endpoint
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.unauthorizedRoute(){
    get(Endpoint.Unauthorized.path){
        call.respond(
            message = ApiResponse<String>(success = false, message = "unauthorized route"),
            status = HttpStatusCode.Unauthorized
        )
    }
}