package codesample.kotlin.sandbox.classes

sealed class Seal

data class SealedName(val name: String) : Seal()
data class SealedAge(val age: Int) : Seal()

/**
 * When dealing with "when expression", you generally have to add else branch.
 * When your expression is a sealed class, you don't have to do it, since it's impossible for
 * sealed class to have any value other then that of it's subclasses.
 */
fun eval (expression: Seal): String = when (expression) {
    is SealedName -> "Sealed name"
    is SealedAge -> "Sealed age"
}

fun main(args: Array<String>) {
    val sealedBro = SealedName("Boris")
    val sealedAge = SealedAge(5)
    println(eval(sealedBro))
    println(eval(sealedAge))

}