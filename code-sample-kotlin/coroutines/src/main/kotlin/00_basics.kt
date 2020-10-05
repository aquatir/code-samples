import kotlinx.coroutines.*

fun mainBasic1() = runBlocking<Unit> {

    val worldJob = GlobalScope.launch {
        delay(1000)
        print(" World!")
    }

    print("Hello,")
    delay(2000)
}

fun mainBasic2() = runBlocking<Unit> { // <- does not complete unless all jobs inside complete

    launch {
        delay(1000)
        print(" World!")
    }
    print("Hello,")
}

fun mainBasic3() = runBlocking {
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

fun mainBasic4() = runBlocking {
    launch { doWorld() }
    print("Hello, ")
}

suspend fun doWorld() {
    delay(1000)
    println("World!")
}

fun mainBasic5() = runBlocking {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // just quit after delay
}
