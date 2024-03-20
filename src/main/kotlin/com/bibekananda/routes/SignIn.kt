package com.bibekananda.routes


import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.bibekananda.data.model.ApiResponse
import com.bibekananda.data.model.auth.LoginRequest
import com.bibekananda.data.model.auth.LoginTokenResponse
import com.bibekananda.data.model.endpoint.Endpoint
import com.bibekananda.domain.UserRepository
import com.bibekananda.util.Constants.JWT_AUDIENCE
import com.bibekananda.util.Constants.JWT_ISSUER
import com.bibekananda.util.Constants.SECRET_KEY
import com.bibekananda.util.Constants.SUB_ID
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.signIn(app: Application, userRepository: UserRepository) {
    post(Endpoint.SignIn.path) {
        val request = call.receive<LoginRequest>()
        if (!request.email.isNullOrEmpty() && !request.password.isNullOrEmpty()) {
                val status = userRepository.loginUser(
                    email = request.email,
                    password = request.password
                )
            if(status.success){
                val token = JWT.create()
                    .withAudience(JWT_AUDIENCE)
                    .withIssuer(JWT_ISSUER)
                    .withClaim(SUB_ID, request.email)
                    .withExpiresAt(Date(System.currentTimeMillis() + 25920000000000L))
                    .sign(Algorithm.HMAC256(SECRET_KEY))
                call.respond(message = ApiResponse(success = true,response = LoginTokenResponse(token = token)),status = HttpStatusCode.OK)
            }else{
                call.respond(message = ApiResponse(success = false,response = null, message = status.message),status = HttpStatusCode.BadRequest)

            }

        } else {
            call.respondRedirect(Endpoint.Unauthorized.path)
        }

    }
}
