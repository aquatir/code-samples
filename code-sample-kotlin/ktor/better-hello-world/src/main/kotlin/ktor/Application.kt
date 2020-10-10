package ktor

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.serialization.*
import io.ktor.util.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.slf4j.event.Level

@Serializable
data class HelloWorld(val word1: String, val word2: String)

@Serializable
data class RequestData(val hello: String)

@Serializable
data class OkResponse(val ok: String = "ok")


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

object MyAttributeKeys {
    val strKey = AttributeKey<String>("atrkey")
    val mapKey = AttributeKey<Map<String, String>>("atrmap")

    val timestart = AttributeKey<Long>("timestart") // use for filter functionality
}

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

    intercept(ApplicationCallPipeline.Call) {
        call.attributes.put(MyAttributeKeys.strKey, "atrValue")
        call.attributes.put(MyAttributeKeys.mapKey, mapOf("key" to "value"))
    }

    routing {
        get("/") {
            val reqHeader = call.request.header("X-Other-Header")
            val hello = HelloWorld("Hello, ", "world!!!")


            call.response.header("X-My-Header", "My-value")
            call.respond(HttpStatusCode.OK, hello)
        }

        get("/map") {

            log.info("att str key: '${call.attributes.get(MyAttributeKeys.strKey)}'")
            log.info("att map key: '${call.attributes.get(MyAttributeKeys.mapKey)}'")
            call.respond(mapOf("key" to "value"))
        }

        get("/params/{requiredParam}/{optionalParam?}") {
            val params = call.request.queryParameters
            log.info("one: '${params["one"]}' two: '${params["two"]}'")

            val req = call.parameters["requiredParam"]
            val part = call.parameters["optionalParam"]
            log.info("path param required: '$req', optiona: '$part'")

            call.respond(HttpStatusCode.OK, OkResponse())
        }

        post<RequestData>("/body") {
            call.respond(it)
        }
    }
}
