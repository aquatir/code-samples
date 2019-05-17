package codesample.kotlin.sandbox.basics

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun printText(a: String) {
    println("You string is: $a")
}

fun getNullString(): String? {
    return null
}

fun printNotNullString(a: String) {
    println(a)
}

fun main(args: Array<String>) {
    println("Hello, Kotlin!")
    println(sum(5, 1))

    printText("Hello, world!")


    val c: Int
    val a = 5 // Read-only variable

    c = a
    println(c)

    val text = "This is Kotlin"
    println("text: ${text.replace("is", "was")}")

    fun maxOfTwo(a: Int, b: Int): Int = if (a > b) a else b

    println("Max of 5 and 10 is: " + maxOfTwo(5, 10))

    var nullStr = getNullString()
    println("$nullStr bla-bla-bla")

    if (nullStr != null) {
        // This will be smart-casted into from String? to String
        printNotNullString(nullStr)
    }

    // We can define function like this
    fun printTextt(text: String) = println(text)

    // Or with body
    fun printTexttWithBody(text: String) = println(text)

    printTextt("text")
    printTexttWithBody("textt")

    fun strLength(something: Any): Int {
        if (something is String) {
            // We can use .length on Any argument, because is gets smart-casted to String
            return something.length
        }
        return 0
    }


    println("\n****** WHEN ******* ")
    fun describe(obj: Any): String =
            when (obj) {
                1 -> "One"
                "Hello" -> "Greeting"
                is Long -> "Long"
                !is String -> "Not a string"
                else -> "Unknown"
            }
    println(describe(1))
    println(describe("Hello"))
    println(describe(30L))
    println(describe(323.12))
    println(describe("Str"))

    val b = 3
    when (b) {
        1, 2 -> println("a is either 1 or 2")
        else -> {
            println("a in neither 1 nor 2")
        }
    }

    println("\n****** RANGES ******* ")
    for (x in 0..5)
        if (x !in 2..3)
            println(x)

    val y = 50
    if (y in 44..56)
        println("y is in 44-56 range")
    for (x in 5 downTo 0 step 2)
        println(x)

    println("\n****** COLLECTIONS AND LAMBDAS ******* ")
    val fruits = listOf("apple", "mango", "avocado", "banana", "apple", null, null)
    fruits
            .map { it.toString() } // null will be "null"
            .filter { it.startsWith("a") } // and they will be filtered out
            .sortedBy { it }
            .distinctBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }

    for ((index, value) in fruits.withIndex()) {
        println("intex: $index value: $value")
    }

    println("\n****** DESTRUCTURING DECLARATION ******* ")
    data class Student(val a :String, val b: Int, val c: String ){
        var name:String = this.a
        var age:Int = this.b
        val subject:String = this.c
    }

    val student = Student("Ivan", 4, "Biology")
    val (param1, param2, param3) = student
    println("Name $param1, age: $param2, subject: $param3")

    fun returnArrayOfInt() : Array<Int> {
        return arrayOf(1,2,3)
    }

    val (value1, value2, value3) = returnArrayOfInt()
    println("value1: $value1, value2: $value2, value3: $value3")

    // Kotlin provides multi-line strings with 3 " characters
    """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin("|").also {
        println(it)
    }

    // We can use labels!
    label@ for (i in 0..100) {
        for (j in 3..100) {
            println("value: $j")
            if (j == 5) {
                println("breaking inner AND outer loops together")
                break@label
            }
        }
    }
}