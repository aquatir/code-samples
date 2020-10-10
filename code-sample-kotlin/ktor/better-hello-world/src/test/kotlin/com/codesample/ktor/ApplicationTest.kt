package com.codesample.ktor

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ApplicationTest {

    suspend fun test(block: suspend (client: HttpClient) -> Unit) {
        var srv: NettyApplicationEngine? = null

        // May cache http client?
        val client = HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
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
    fun testOther() = runBlocking {
        test { client ->
            val res = client.get<String>("http://localhost:8080/other")

            assertEquals("""
                {"status":"ok"}
            """.trimIndent(), res)
        }
    }

}
