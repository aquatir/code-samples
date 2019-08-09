package codesample.kotlin.ktor

import codesample.kotlin.ktor.api.DefaultApi
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
import io.ktor.locations.Locations
import io.ktor.request.queryString
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.yaml.snakeyaml.Yaml
import java.util.*


sealed class Component(open val nodeName: String)
class Leaf(override val nodeName: String) : Component(nodeName) {
    fun getValue() = nodeName
}

class Composite(override val nodeName: String, private val children: MutableList<Component> = mutableListOf()) :
    Component(nodeName) {
    fun addChild(child: Component) = children.add(child)
    fun getChildren() = children
}

class PropertyReader(map: MutableMap<String, Any>) {

    private val propertiesHolder = Composite("head")

    init {
        val first = mapOf("head" to map)
        readMapRecursively(propertiesHolder, first.getValue("head"))
    }

    /**
     * read property from [map] passed in when this class is created
     *
     * Composite is used here instead of Map<String, Any> to ensure type-safety and to make traversal easier to update if
     * required */
    fun readProperty(path: String): String? {
        val splittedPath = path.split("/")
        var curPlaceInTree = propertiesHolder as Component

        var curNumOfTraversed = 0
        val targetNumOfTraversed = splittedPath.size
        for (curPath: String in splittedPath) {
            when (curPlaceInTree) {
                is Leaf -> if (curNumOfTraversed == targetNumOfTraversed) return curPlaceInTree.nodeName // Is it reachable?
                is Composite -> {
                    when (val possibleChild = curPlaceInTree.getChildren().find { it.nodeName == curPath }) {
                        null -> return null
                        else -> {
                            curNumOfTraversed++
                            curPlaceInTree = possibleChild
                        }
                    }
                }
            }
        }
        return if (curNumOfTraversed == targetNumOfTraversed && curPlaceInTree.nodeName == splittedPath.last()) {

            return when(curPlaceInTree) {
                is Leaf -> curPlaceInTree.nodeName // Is reachable?
                is Composite -> {
                    curPlaceInTree.getChildren()
                        .takeIf { it.size == 1 }
                        ?.first()?.nodeName
                }
            }
        }
        else null
    }

    fun readPropertyOrThrow(path: String): String = readProperty(path)!!

    private fun readMapRecursively(composite: Composite, mutableMap: MutableMap<String, Any>) {
        mutableMap.forEach { (key, value) ->
            when (value) {
                is String -> composite.addChild(Composite(nodeName = key, children = mutableListOf(Leaf(value))))
                else -> {
                    val innerComp = Composite(key)
                    composite.addChild(innerComp)
                    readMapRecursively(innerComp, value as MutableMap<String, Any>)

                }
            }
        }
    }
}



private fun createProducer(brokers: String): Producer<String, String> {
    val props = Properties()
        .apply {
            this["bootstrap.servers"] = brokers
            this["key.serializer"] = StringSerializer::class.java.canonicalName
            this["value.serializer"] = StringSerializer::class.java.canonicalName
        }
    return KafkaProducer(props)
}



fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

/**
 * Что нужно:
 * +/- Генериться из openApi (генерится, но нет артефактор для расширения)
 * + Подгружать конфиги из консула
 * Работать с кафкой, как в качестве consumer, так и в качестве producer
 * Работать с postgres (из jooq)
 * Уметь подниматься без окружения для тестов
 * + Собираться в docker образ
 * Собираться через gitlab-ci
 * Уметь в health-check
 */

@kotlin.jvm.JvmOverloads
fun Application.main(testing: Boolean = false) {

    // CONSUL
    val consulClient = Consul
        .builder()
        .withHostAndPort(HostAndPort.fromString("localhost:8500"))
        .build()
    val kvClient = consulClient.keyValueClient()

    val yamlReader = Yaml()
    val common = yamlReader.load<MutableMap<String, Any>>(kvClient.getValueAsString("config/common/data").get())
    val service = yamlReader.load<MutableMap<String, Any>>(kvClient.getValueAsString("config/hello_world/data").get())

    val propReaderCommon = PropertyReader(common)
    val propReaderService = PropertyReader(service)

    val prop1 = propReaderCommon.readProperty("common1/conf1")
    val prop2 = propReaderCommon.readProperty("common2")

    val prop3 = propReaderService.readProperty("specific1/conf1")
    val prop4 = propReaderService.readProperty("specific2")

    val resultNum = this.environment.config.propertyOrNull("custom_constant.value_num")?.getString()?.toInt()
    val resultStr = this.environment.config.propertyOrNull("custom_constant.value_str")?.getString()

    println("$prop1, $prop2 $prop3 $prop4 $resultNum $resultStr")

    // KAFKA
    val producer = createProducer(propReaderCommon.readPropertyOrThrow("kafka/url"))

    val sendResult = producer.send(ProducerRecord("new_topic", "value")).get()

    println("offset: ${sendResult.offset()} partition: ${sendResult.partition()}")




    // Actual application
    install(Authentication) {
    }

    install(ContentNegotiation) {
        gson {
        }
    }
    install(Locations)

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
        }*/

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

        post<Body>("/body") {
            call.respond(JsonSampleClass("successful received body with name: ${it.name}, age: ${it.age}"))
        }

        DefaultApi()



    }
}




data class JsonSampleClass(val hello: String)
data class Body(val name: String, val age: Int)
