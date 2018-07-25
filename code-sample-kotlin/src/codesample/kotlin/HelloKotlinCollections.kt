package codesample.kotlin

fun main(args: Array<String>) {
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
}