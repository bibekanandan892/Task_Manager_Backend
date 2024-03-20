package com.bibekananda.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.bibekananda.data.model.ApiResponse
import com.bibekananda.data.model.auth.SignUpRequest
import com.bibekananda.data.model.endpoint.Endpoint
import com.bibekananda.domain.UserRepository
import com.bibekananda.util.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.signUp(app: Application, userRepository: UserRepository) {
    post(Endpoint.SingUp.path) {
        val request = call.receive<SignUpRequest>()
        if (!request.emailAddress.isNullOrEmpty() && !request.password.isNullOrEmpty() && !request.name.isNullOrEmpty()) {

            val status = userRepository.signUpUser(

                email = request.emailAddress,
                password = request.password,
                name = request.name
            )
            if(status.success){
                val token = JWT.create()
                    .withAudience(Constants.JWT_AUDIENCE)
                    .withIssuer(Constants.JWT_ISSUER)
                    .withClaim(Constants.SUB_ID, request.emailAddress)
                    .withExpiresAt(Date(System.currentTimeMillis() + 25920000000000L))
                    .sign(Algorithm.HMAC256(Constants.SECRET_KEY))
                call.respond(message =  ApiResponse(success = true, response = token, message = status.message), status = HttpStatusCode.OK)
            }else{
                call.respond(message = ApiResponse(success = false,response = null, message = status.message),status = HttpStatusCode.BadRequest)

            }

        } else {
            call.respondRedirect(Endpoint.Unauthorized.path)
        }

    }
}