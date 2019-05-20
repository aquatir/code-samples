package codesample.kotlin.sandbox.basics

/** Run this func in test BasicsRunner */
fun lambdas() {
    val addTwo: (Int) -> Int = { input: Int -> input + 2 }
    println("adding 2 to 3, expecting 5, actual: ${addTwo(3)}")
    println("adding 2 to 3 two times, expecting 7, actual: ${applyIntFuncTwice(3, addTwo)}")
}

fun applyIntFuncTwice(a: Int, func: (Int) -> Int): Int {
    return func(func(a))
}