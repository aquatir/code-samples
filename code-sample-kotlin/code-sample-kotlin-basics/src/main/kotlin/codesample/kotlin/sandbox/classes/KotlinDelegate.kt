package codesample.kotlin.sandbox.classes

import kotlin.reflect.KProperty

interface VeryUsefullFunction {
    fun printInputWithGreeting(input: String)
}

class VeryUsefullFunctionImpl : VeryUsefullFunction {
    override fun printInputWithGreeting(input: String) {
        println("Hello, $input!")
    }
}

/** DelegatorForVeryUsefullFunction class can now use functions and properties defined in VeryUsefullFunction,
 * if an instance VeryUsefullFunction is passed in. See an example */
class DelegatorForVeryUsefullFunction(base: VeryUsefullFunction) : VeryUsefullFunction by base


interface SomeBehavior {
    fun printStuff() = println("Hello")
}

class Delegated(base: SomeBehavior): SomeBehavior by base {
    override fun printStuff() {
        super.printStuff()
        println(" And more!")
    }
}



class SomethingWithDelegateProperties {
    val propertyWithDelegation: String by PrintingDelegate()
    val lazy: String by lazy {
        println("computed!")
        "Hello"
    }
}

class PrintingDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }
}

fun main(args: Array<String>) {
    val funcClass = VeryUsefullFunctionImpl()
    DelegatorForVeryUsefullFunction(funcClass).printInputWithGreeting("Ivan")

    println()

    val defaultBehavior = object : SomeBehavior {}
    val delegated = Delegated(defaultBehavior)
    defaultBehavior.printStuff()
    delegated.printStuff()

    val objDelegatesProperties = SomethingWithDelegateProperties()
    println(objDelegatesProperties.propertyWithDelegation)
    println(objDelegatesProperties.lazy)
    println(objDelegatesProperties.lazy)
}