import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun mainCtxAndDispatchers() = runBlocking<Unit> {
    // delays to test debug

    println("My job is ${coroutineContext[Job]}")
    launch { // context of the parent, main runBlocking coroutine
        delay(50)
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        //println("main runBlocking      : job is ${coroutineContext[Job]}")
    }
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        delay(50)
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
        delay(50)
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }

    Executors.newSingleThreadExecutor().asCoroutineDispatcher().use {
        launch(it) { // will get its own new thread
            delay(50)
            println("newSingleThreadExecutor: I'm working in thread ${Thread.currentThread().name}")
        }
    }
}
