package codesample.kotlin.graalvm.config

import codesample.kotlin.graalvm.Application
import com.google.common.net.HostAndPort
import com.orbitz.consul.Consul
import com.orbitz.consul.KeyValueClient
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import java.lang.IllegalArgumentException

/**
 * Класс для взаимодействия с консулом.
 *
 * ВАЖНО! вызовите [init] для инициализации.
 */
class ConsulConfiguration (private val consulHostPort: String) {

    private val log = LoggerFactory.getLogger(ConsulConfiguration::class.java)

    private lateinit var kvClient: KeyValueClient
    private lateinit var yamlReader: Yaml

    /** Здесь специально метод init вынесен, чтобы в унит тестах мы могли его заменить на пустышку и безопасно подемнять
     * методы для получения параметров */
    public fun init(): ConsulConfiguration {
        val consulClient = Consul
                .builder() //    consul.mynet:8500
                .withHostAndPort(HostAndPort.fromString(consulHostPort))
                .build()
        kvClient = consulClient.keyValueClient()
        yamlReader = Yaml()
        return this
    }

    public fun readPropertyYaml(path: String): PropertyReader {
        return PropertyReader(this.yamlReader.load<MutableMap<String, Any>>(kvClient.getValueAsString(path).get()))
    }

}


/*
 *
 *
 *
 */

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

    private val log = LoggerFactory.getLogger(PropertyReader::class.java)

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
    fun readProperty(path: String, delimiters: String = "."): String? {
        val splittedPath = path.split(delimiters)
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

    fun readPropertyOrThrow(path: String, delimiters: String = "."): String {
        val prop = readProperty(path, delimiters)
        if (prop != null)
            return prop
        else {
            val errorStr = "No property found by path: $path delimiters: $delimiters"
            this.log.error(errorStr)
            throw IllegalArgumentException(errorStr)
        }
    }

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

