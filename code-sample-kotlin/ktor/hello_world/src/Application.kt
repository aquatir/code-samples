package codesample.kotlin.ktor

import com.google.common.net.HostAndPort
import com.orbitz.consul.Consul
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import kotlinx.coroutines.*
import io.ktor.client.features.logging.*
import io.ktor.gson.gson
import io.ktor.request.queryString
import org.yaml.snakeyaml.Yaml


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


sealed class Component(open val nodeName: String)
class Leaf(override val nodeName: String): Component(nodeName) {
    fun getValue() = nodeName
}
class Composite(override val nodeName: String, private val children: MutableList<Component> = mutableListOf()): Component(nodeName) {
    fun addChild(child: Component) = children.add(child)
    fun getChildren() = children
}

fun readMapRecursively(composite: Composite, mutableMap: MutableMap<String, Any>) {
    mutableMap.forEach { (key, value) ->
        when(value) {
            is String -> composite.addChild(Composite(nodeName = key, children = mutableListOf(Leaf(value))))
            else -> {
                val innerComp = Composite(key)
                composite.addChild(innerComp)
                readMapRecursively(innerComp, value as MutableMap<String, Any>)

            }
        }
    }
}

fun tryReadProperty(path: String, propertyTree: Composite): String? {
    val splittedPath = path.split("/")
    var curPlaceInTree = propertyTree as Component

    var curNumOfTraversed = 0
    val targetNumOfTraversed = splittedPath.size
    for (curPath: String in splittedPath) {
        when (curPlaceInTree) {
            is Leaf -> if (curNumOfTraversed == targetNumOfTraversed) return curPlaceInTree.nodeName
            is Composite -> {
                val possibleChild = curPlaceInTree.getChildren().find { it.nodeName == curPath }
                if (possibleChild == null) {
                    return null
                }
                else {
                    curNumOfTraversed++
                    curPlaceInTree = possibleChild
                }
            }
        }
    }
    return if (curNumOfTraversed == targetNumOfTraversed && curPlaceInTree.nodeName == splittedPath.last())
        curPlaceInTree.nodeName
    else null
}

@kotlin.jvm.JvmOverloads
fun Application.main(testing: Boolean = false) {

    val consulClient = Consul
            .builder()
            .withHostAndPort(HostAndPort.fromString("localhost:8500"))
            .build()
    val kvClient = consulClient.keyValueClient()
    val dirClient = consulClient.catalogClient()
    val key = kvClient.getValueAsString("myservice")

    val asYaml = Yaml()
    val result = asYaml.load<MutableMap<String, Any>>(key.get())

    val first = mapOf("head" to result)
    val composite = Composite("head")
    readMapRecursively(composite, first.getValue("head"))

    val prop1 = tryReadProperty("mykey/subkey1", composite)
    val prop2 = tryReadProperty("other", composite)
    println(result)


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
