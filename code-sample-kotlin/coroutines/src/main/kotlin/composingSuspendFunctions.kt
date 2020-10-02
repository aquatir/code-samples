import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        try {
            failedConcurrentSum()
        } catch(e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }
    println("Total time spent: ${time}ms")

}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        delay(1000)
        println("Second child throws an exception")
        throw ArithmeticException() // this throw will stop upper coroutine because they are in the same scope
    }
    one.await() + two.await()
}

fun mainComposing2() = runBlocking {
    val time = measureTimeMillis {
        println("sum ${concurrentSum()}")
    }
    println("time taken: $time")

}

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}


fun mainComposing1() = runBlocking {
    val time = measureTimeMillis {
        //val one = async { doSomethingUsefulOne() }
        //val two = async { doSomethingUsefulTwo() }

//        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
//        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
//        one.start()
//        two.start()

        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}

fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// The result type of somethingUsefulTwoAsync is Deferred<Int>
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}


suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}
