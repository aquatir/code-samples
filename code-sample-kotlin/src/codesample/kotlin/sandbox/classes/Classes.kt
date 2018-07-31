package codesample.kotlin.sandbox.classes

typealias StringSet = Set<String>

fun printSetOfString(setOfStrings: Set<String>) {
    setOfStrings.forEach { println(it) }
}

/** Everything is Kotlin is final by default, so we have to declare class as open for inheritance */
open class TheSuper(val name: String) {

    constructor() : this("default_name")

    /* The same goes for methods */
    open fun printName() {
        println(name)
    }

    class InnerStatic {
        private val innerStaticName: String = "Static Blah-blah"
        fun printInnerStatic() {
            println(innerStaticName)
        }
    }

    inner class Inner {
        private val innerName: String = "Inner Blah-blah"
        fun printInner() {
            println(innerName)
        }
    }
}

class TheSub : TheSuper() {
    override fun printName() {
        println("The sub calls for you, $name")
    }
}

interface Runnnnable {
    fun run()
}

fun main(args: Array<String>) {
    val innerStatic = TheSuper.InnerStatic()
    innerStatic.printInnerStatic()

    val inner = TheSuper().Inner()
    inner.printInner()

    val human: Runnnnable = object : Runnnnable {
        override fun run() {
            println("I RUN LIKE A HUMAN")
        }
    }
    human.run()

    /* Kotlin supports type aliases. It also supports passing named parameters to function */
    val strSet: StringSet = setOf("one", "two", "three")
    printSetOfString(setOfStrings = strSet)

    val superb = TheSuper("My name is IVAAAAAn")
    superb.printName()

    val sub = TheSub()
    sub.Inner()
}

