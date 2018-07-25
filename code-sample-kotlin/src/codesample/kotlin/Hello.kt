package codesample.kotlin

fun sum (a: Int, b: Int) : Int {
    return a + b
}

fun printText(a: String) {
    println("You string is: $a")
}

fun getNullString() : String? {
    return null
}

fun main(args: Array<String>) {
    println("Hello, Kotlin!")
    println(sum(5,1))

    printText("Hello, world!")


    val c: Int
    val a = 5 // Read-only variable

    c = a
    println(c)

    val text = "This is Kotlin"
    println("text: ${text.replace("is", "was")}")

    fun maxOfTwo(a: Int, b: Int) : Int = if (a > b) a else b

    println("Max of 5 and 10 is: " + maxOfTwo(5,10))

    val nullStr = getNullString()
    println("$nullStr bla-bla-bla")
}