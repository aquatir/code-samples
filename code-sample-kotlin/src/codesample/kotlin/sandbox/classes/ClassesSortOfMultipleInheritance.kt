package codesample.kotlin.sandbox.classes


interface One {
    fun doSomething() {
        println("One flying!")
    }
}

interface Two {
    fun doSomething() {
        println("Two flying!")
    }
}

/**
 * If both interfaces have a method with the same signature, you ccan override it once.
 * You can also call respective super methods if required
 */
class OneTwoImplementor: One, Two {
    override fun doSomething() {
        super<One>.doSomething()
        super<Two>.doSomething()
    }
}


fun main(args: Array<String>) {
    val one: One = OneTwoImplementor()
    val two: Two = OneTwoImplementor()

    one.doSomething()
    two.doSomething()
}