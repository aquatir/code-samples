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
 * If both interfaces have a method with the same signature, you can override it once.
 * You can also call respective super methods if required
 */
class OneTwoImplementor : One, Two {
    override fun doSomething() {
        super<One>.doSomething()
        super<Two>.doSomething()
    }

    /**
     * Kotlin's way to static methoods is companion objects!
     */
    companion object {
        fun behaveLikeStaticButItsNot() {
            println("I'm not actually static")
        }

        const val sortOfStaticValue = "Ivaaan"
    }
}


fun main(args: Array<String>) {
    val one: One = OneTwoImplementor()
    val two: Two = OneTwoImplementor()
    val oneTwo = OneTwoImplementor()

    one.doSomething()
    two.doSomething()
    oneTwo.doSomething()


    println(one.yetAnotherInterfaceFunction())
    println(oneTwo.yetAnotherInterfaceFunction())
    OneTwoImplementor.behaveLikeStaticButItsNot()
    println(OneTwoImplementor.sortOfStaticValue)
    // two.yetAnotherInterfaceFunction() DOESN'T WORK!

}

/**
 * This is called extensions. We add yet another function to interface, and all classes implementing
 * this interface can now use this function. Note: Classes can not override this function
 */
fun One.yetAnotherInterfaceFunction(): String {
    return "another interface function"
}