package com.bibekananda.routes

import com.bibekananda.data.model.ApiResponse
import com.bibekananda.data.model.endpoint.Endpoint
import com.bibekananda.util.Constants.SUB_ID
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authorizedRoute() {
    authenticate("jwt-auth") {
        get(Endpoint.Authorized.path) {
            val principal = call.authentication.principal<JWTPrincipal>()
            val userId = principal?.payload?.getClaim(SUB_ID)?.asString()
            call.respond(
                message = ApiResponse<String>(success = true, message = userId),
                status = HttpStatusCode.OK
            )
        }
    }
}