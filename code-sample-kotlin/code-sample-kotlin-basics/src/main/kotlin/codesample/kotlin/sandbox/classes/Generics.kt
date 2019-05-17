package codesample.kotlin.sandbox.classes


/**
 * Basic generic example
 */
class GenericExample<T> (private val value: T) {
    fun printT() {
        println("Generic value is: $value")
    }
}


/**
 * "In" and "Out" examples
 */
open class TheTopClass (val value: String) {

    override fun toString(): String {
        return "The supper value: $value"
    }
}

open class TheMiddleClass (value : String): TheTopClass(value) {
    override fun toString(): String {
        return "The middle value: $value"
    }
}

class TheBottomClass (value: String): TheMiddleClass(value) {
    override fun toString(): String {
        return "The middle value: $value"
    }
}

class GenericValue<T> (private val value: T) {
    fun print() {
        println(value.toString())
    }
}

/**
 * <out CLASS> accept instances of this class (TheMiddleClass) AND any SUB classes
 */
fun acceptGenericValueOut(middleOrBottom : GenericValue<out TheMiddleClass>) {
    middleOrBottom.print()
}

/**
 * <in CLASS> accept instances of this class (TheMiddleClass) AND any SUPER classes
 */
fun acceptGenericValueIn(middleOrTop : GenericValue<in TheMiddleClass>) {
    middleOrTop.print()
}

fun main(args: Array<String>) {
    val genericOfString : GenericExample<String> = GenericExample("kek")
    genericOfString.printT()

    val genericOfInt : GenericExample<Int> = GenericExample(3)
    genericOfInt.printT()

    println("\n********** IN AND OUT EXAMPLE *********\n")

    val top = TheTopClass("Fiz")
    val middle = TheMiddleClass("Baz")
    val bottom = TheBottomClass("Alas")

    val genericTop = GenericValue(top)
    val genericMiddle = GenericValue(middle)
    val genericBottom = GenericValue(bottom)

    acceptGenericValueIn(genericTop)
    acceptGenericValueIn(genericMiddle)
    //acceptGenericValueIn(genericBottom) // Doesn't work. <in Class> only accept this class and SUPER classes

    //acceptGenericValueOut(genericTop) // Doesn't work. <out Class> only accept this class and SUB classes
    acceptGenericValueOut(genericMiddle)
    acceptGenericValueOut(genericBottom)
}