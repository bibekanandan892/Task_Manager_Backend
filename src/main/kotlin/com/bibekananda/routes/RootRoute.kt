package com.bibekananda.routes

import com.bibekananda.data.model.endpoint.Endpoint
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.rootRoute() {
    get(Endpoint.Root.path) {
        call.respondText("WELCOME TO WHERE IS MY HEART")
    }
}