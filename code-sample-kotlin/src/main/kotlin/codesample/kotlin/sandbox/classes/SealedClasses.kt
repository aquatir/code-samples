package codesample.kotlin.sandbox.classes

sealed class Seal

data class SealedName(val name: String) : Seal()
data class SealedAge(val age: Int) : Seal()

/**
 * When dealing with when expression, you generally have to add else branch.
 * When your expression is a sealed class, you don't have to do it, since all the possible types can be
 * determined in runtime
 */
fun eval (expression: Seal): String = when (expression) {
    is SealedName -> "Sealed name"
    is SealedAge -> "Sealed age"
}

/** Run this func in test BasicsRunner */
fun kotlinSealedClasses() {
    val sealedBro = SealedName("Boris")
    val sealedAge = SealedAge(5)
    println(eval(sealedBro))
    println(eval(sealedAge))

}