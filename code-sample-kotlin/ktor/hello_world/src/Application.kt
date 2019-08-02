package codesample.kotlin.ktor

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import kotlinx.coroutines.*
import io.ktor.client.features.logging.*
import io.ktor.request.queryString


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.main(testing: Boolean = false) {
    install(Authentication) {
    }

    install(ContentNegotiation) {
        gson {
        }
    }

    val client = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Logging) {
            level = LogLevel.HEADERS
        }
    }
    runBlocking {
        // Sample for making a HTTP Client request
        /*
        val message = client.post<JsonSampleClass> {
            url("http://127.0.0.1:8080/path/to/endpoint")
            contentType(ContentType.Application.Json)
            body = JsonSampleClass(hello = "world")
        }
        */
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/gson") {
            call.respond(JsonSampleClass("keks"))
        }

        get("/get/{id}/now") {
            val id = call.parameters["id"]
            val first = call.request.queryParameters["param1"]
            val second = call.request.queryParameters["param2"]
            val third = call.request.queryParameters["param3"]
            call.respond(JsonSampleClass("$id $first $second $third qString: ${call.request.queryString()}"))
        }

        post<Body>("/body/{some}/{parameters}/{optional?}") {
            val some = call.parameters["some"]!!
            val parameters = call.parameters["parameters"]!!
            val optional = call.parameters["optional"]
            call.respond(JsonSampleClass("$some $parameters $optional"))
        }

        post<Body> ("/body") {
            call.respond(JsonSampleClass("successful received body with name: ${it.name}, age: ${it.age}"))
        }
    }
}


data class JsonSampleClass(val hello: String)
data class Body(val name: String, val age: Int)
