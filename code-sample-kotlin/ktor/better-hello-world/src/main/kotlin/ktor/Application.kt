package ktor

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.serialization.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.slf4j.event.Level

@Serializable
data class HelloWorld(val word1: String, val word2: String)

@Serializable
data class RequestData(val hello: String)


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ContentNegotiation) {
        json(
            contentType = ContentType.Application.Json,
            json = Json {
                encodeDefaults = true
                useArrayPolymorphism = true
                ignoreUnknownKeys = true
            }
        )
    }

    routing {
        get("/") {
            val reqHeader = call.request.header("X-Other-Header")
            val hello = HelloWorld("Hello, ", "world!")
            call.response.header("X-My-Header", "My-value")
            call.respond(HttpStatusCode.OK, hello)
        }

        get("/map") {
            call.respond(mapOf("key" to "value"))
        }

        post<RequestData>("/body") {
            call.respond(it)
        }
    }
}
