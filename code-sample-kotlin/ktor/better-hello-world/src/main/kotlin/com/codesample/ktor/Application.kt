package com.codesample.ktor

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.slf4j.MDCContext
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.slf4j.MDC
import org.slf4j.event.Level
import java.lang.RuntimeException

@Serializable
data class HelloWorld(val word1: String, val word2: String)

@Serializable
data class RequestData(val hello: String)

@Serializable
data class StatusResponse(val status: Status = Status.OK)

enum class Status {
    OK, NOT_OK, UNKNOWN
}

object MyAttributeKeys {
    val strKey = AttributeKey<String>("atrkey")
    val mapKey = AttributeKey<Map<String, String>>("atrmap")

    val timestart = AttributeKey<Long>("timestart") // use for filter functionality
}

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
fun main(args: Array<String>) {
    server(false).start(true)
}

fun server(test: Boolean): NettyApplicationEngine {
    return embeddedServer(Netty,
        port = 8080,
        //watchPaths = listOf("/build/classes/kotlin"), // TODO: Fails with 'Module function provided as lambda cannot be unlinked for reload'
        configure = {
            responseWriteTimeoutSeconds = 10
        }) {

        // installs

        install(CallLogging) {
            mdc("request_id") {
                it.request.header("some-header") ?: "N/A"
            }
            mdc("object") { "id" }

            level = Level.INFO
            format { "Request ${it.request.httpMethod.value} on '${it.request.uri}' - status: ${it.response.status()?.value ?: "unset"}. Took: ${System.currentTimeMillis() - it.attributes.get(MyAttributeKeys.timestart)}ms" }
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

        install(StatusPages) {
            exception<ServerException> { _ ->
                call.respond(HttpStatusCode.InternalServerError, StatusResponse(Status.NOT_OK))
            }
            exception<RuntimeException> {
                call.respond(HttpStatusCode.InternalServerError, StatusResponse(Status.UNKNOWN))
            }
        }

        // interceptors

        intercept(ApplicationCallPipeline.Setup) {
            call.attributes.put(MyAttributeKeys.timestart, System.currentTimeMillis())
        }

        intercept(ApplicationCallPipeline.Call) {
            call.attributes.put(MyAttributeKeys.strKey, "atrValue")
            call.attributes.put(MyAttributeKeys.mapKey, mapOf("key" to "value"))
        }

        this.environment.monitor.subscribe(ApplicationStarted) {
            log.info("Caught 'ApplicationStarted' event")
        }


        suspend fun susFunc() = coroutineScope {
            launch {
                log.info("first")
            }
            launch {
                log.info("second")
            }
        }

        routing {
            get("/") {

                val reqHeader = call.request.header("X-Other-Header")

                val hello = HelloWorld("Hello, ", "world!!!")
                log.info("log stuff!")

                // This key will not be available in next suspension point (in func below for example)
                MDC.put("reason", "value")
                susFunc()
                // It will however be available at CallLogging level

                println()

                // We CAN propagate this context like so:
                // (Notice that values are lost after this context is done with)
                withContext(MDCContext()) {
                    susFunc()
                }

                call.response.header("X-My-Header", "My-value")
                call.respond(HttpStatusCode.OK, hello)
            }

            get("/multilog") {
                for (i in 1..100) {
                    launch(Dispatchers.IO) {
                        log.info("logging $i to check MDC")
                    }
                }
            }


            get("/map") {
                log.info("att str key: '${call.attributes[MyAttributeKeys.strKey]}'")
                log.info("att map key: '${call.attributes[MyAttributeKeys.mapKey]}'")
                call.respond(mapOf("key" to "value"))
            }

            get("/params/{requiredParam}/{optionalParam?}") {
                val params = call.request.queryParameters
                log.info("one: '${params["one"]}' two: '${params["two"]}'")

                val req = call.parameters["requiredParam"]
                val part = call.parameters["optionalParam"]
                log.info("path param required: '$req', optional: '$part'")

                call.respond(HttpStatusCode.OK, StatusResponse())
            }

            get("/ex1") {
                throw ServerException("ex!")
            }
            get("/ex2") {
                throw RuntimeException("ex")
            }

            get("/multiparam/{restOfPath...}") {
                val params = call.parameters.getAll("restOfPath")
                log.info("params: $params")
                call.respond(HttpStatusCode.OK, StatusResponse())
            }


            other()

            post<RequestData>("/body") {
                call.respond(StatusResponse())
            }

        }
    }

}


