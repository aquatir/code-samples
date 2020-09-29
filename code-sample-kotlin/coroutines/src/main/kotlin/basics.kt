import kotlinx.coroutines.*

fun main1() = runBlocking<Unit> {

    val worldJob = GlobalScope.launch {
        delay(1000)
        print(" World!")
    }

    print("Hello,")
    delay(2000)
}

fun main2() = runBlocking<Unit> { // <- does not complete unless all jobs inside complete

    launch {
        delay(1000)
        print(" World!")
    }
    print("Hello,")
}

fun main3() = runBlocking {
    launch {
        delay(200)
        println("Task from runBlocking")
    }

    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before the nested launch
    }

    println("Coroutine scope is over")
}

fun main4() = runBlocking {
    launch { doWorld() }
    print("Hello, ")
}

suspend fun doWorld() {
    delay(1000)
    println("World!")
}

fun main() = runBlocking {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // just quit after delay
}
