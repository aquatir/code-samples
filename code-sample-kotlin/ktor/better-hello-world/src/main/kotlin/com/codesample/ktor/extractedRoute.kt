package com.codesample.ktor

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.other() {
    get("/other") {
        call.respond(StatusResponse())
    }
}
