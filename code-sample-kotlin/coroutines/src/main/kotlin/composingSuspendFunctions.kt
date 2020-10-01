import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

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
