package com.codesample.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.netty.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import java.nio.ByteBuffer
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ApplicationTest {

    suspend fun test(block: suspend (client: HttpClient) -> Unit) {
        var srv: NettyApplicationEngine? = null

        // May cache http client?
        val client = HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    json = kotlinx.serialization.json.Json {
                        encodeDefaults = true
                        useArrayPolymorphism = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }

        val kek = (arrayOf("gid", arrayOf("status", "c", "d"), 3))

        // Is it okay to start on save port always? Maybe configure port independently
        try {
            srv = server(true).start(false)
            block.invoke(client)
        } finally {
            client.close()
            srv?.stop(0, 1)
        }
    }

    @Test
    fun testMap() = runBlocking {
        test { client ->
            val res = client.get<Map<String, String>>("http://localhost:8080/map")

            assertEquals(mapOf("key" to "value"), res)
            assertTrue(true)
        }
    }


    @Test
    fun testHttpClientHttpResponse() = runBlocking {
        test { client ->
            val res = client.get<HttpResponse>("http://localhost:8080/map")
            assertEquals(200, res.status.value)

            val body = res.receive<Map<String, String>>()

            val dataAsJson = Json.encodeToString(body)
            assertEquals("""
                {"key":"value"}
            """.trimIndent(), dataAsJson)
        }
    }

    @Test
    fun testHttpClientPost() = runBlocking {
        test { client ->
            val res = client.post<StatusResponse>("http://localhost:8080/body") {
                body = RequestData("hello")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                timeout {
                    requestTimeoutMillis = 5000
                }
            }

            assertEquals(Status.OK, res.status)

            val dataAsJson = Json {
                encodeDefaults = true
            }.encodeToString(res)

            assertEquals("""
                {"status":"OK"}
            """.trimIndent(), dataAsJson)
        }
    }

    @Test
    fun testOther() = runBlocking {
        test { client ->
            val res = client.get<String>("http://localhost:8080/other")

            assertEquals("""
                {"status":"ok"}
            """.trimIndent(), res)
        }
    }


}
