package codesample.kotlin.sandbox.basics

fun main(args: Array<String>) {

    println("******* LISTS ********")
    val mutableList : MutableList<Int> = mutableListOf(1, 2)
    mutableList.add(3)

    val immutableList : List<Int> = mutableList

    println("Mutable list before add:")
    println(mutableList.joinToString(", "))

    println("Immutable list before add:")
    println(immutableList.joinToString(", "))

    /* Immutable view does not contain any methods to add elements HOWEVER it's still
    a reference to mutable list, so adding an element in mutable list will make it
    appear in immutable list
     */
    mutableList.add(4)
    println("Mutable list after add:")
    println(mutableList.joinToString(", "))
    println("Immutable list after add:")
    println(immutableList.joinToString(", "))


    println("\n******* MAPS ********")
    val map = hashMapOf("foo" to 1, "bar" to 2, "fizz" to 3, "buzz" to 4)
    map["kek"] = 6

    println("map keys and values")
    map.entries.forEach { println("key: ${it.key} value: ${it.value}") }
}