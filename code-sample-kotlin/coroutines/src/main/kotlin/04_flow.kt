import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis



///
///
///

fun mainFlow14() = runBlocking<Unit> {
    (1..5).asFlow().collect { value -> // no cancel
        if (value == 3) cancel()
        println(value)
    }
}

fun mainFlow13() = runBlocking<Unit> {
    (1..5).asFlow().cancellable().collect { value ->  // make cancelable
        if (value == 3) cancel()
        println(value)
    }
}


//
//
//

fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(1000) }

fun mainFlow12() = runBlocking {
    events()
            .onEach { event -> println("Event: $event") }
            .launchIn(this)
    println("Done")
}

///
///
///

fun mainFlow11() = runBlocking {
    flow {
        (1..5).forEach {
            delay(100)
            emit(it)
        }
    }
            .onEach { value ->
                check(value % 2 == 1) { "Collected $value" }
            }
            .catch { _ ->
                println("Caught ex. Emitting 3")
                emit(3)
            }
            .collect() {        // after ex, no more values may be emitted
                println("collect $it")
            }
}

fun mainFlow10() = runBlocking {
    simpleFlow6()
            .onCompletion { println("Done") }
            .collect { value -> println(value) }
}


fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

fun mainFlow9() = runBlocking {
    val startTime = System.currentTimeMillis() // remember the start time

    // flowMapConcat
//    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
//            .flatMapConcat { requestFlow(it) }
//            .collect { value -> // collect and print
//                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//            }
    // flowMapMerge
//    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
//            .flatMapMerge { requestFlow(it) }
//            .collect { value -> // collect and print
//                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
//            }

    // flowMapLatest
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
            .flatMapLatest { requestFlow(it) }
            .collect { value -> // collect and print
                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
            }

}

///
///
///

fun mainFlow8() = runBlocking {
    val nums = (1..4).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
    val startTime = System.currentTimeMillis() // remember the start time
    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine"
            .collect { value -> // collect and print
                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
            }
}

fun mainFlow7() = runBlocking {
    val nums = (1..3).asFlow() // numbers 1..3
    val strs = flowOf("one", "two", "three", "four") // strings
    nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string
            .collect { println(it) } // collect and print
}

fun simpleFlow6(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i) // emit next value
    }
}

fun mainFlow6() = runBlocking<Unit> {
    val time = measureTimeMillis {
        simpleFlow6()
                // .buffer()
                //.conflate()
                .collectLatest { value -> // cancel & restart on the latest value
                    println("Collecting $value")
                    delay(300) // pretend we are processing it for 300 ms
                    println("Done $value")
                }
//                .collect { value ->
//                    delay(300)
//                    println(value)
//                }
    }
    println("Collected in $time ms")
}


fun simpleFlow5(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(1000) // pretend we are computing it in CPU-consuming way
        log("Emitting $i")
        emit(i) // emit next value
    }
}.flowOn(Dispatchers.Default) // RIGHT way to change context for CPU-consuming code in flow builder

fun mainFlow5() = runBlocking<Unit> {
    simpleFlow5().collect { value ->
        log("Collected $value")
    }
}


fun Flow<Int>.double(): Flow<Int> {
    return this.map { it * it }
}

fun flowMain4() = runBlocking {
    (1..3).asFlow()
            .double()
            .double()
            .collect { println(it) }

}

fun flowSimple3(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun flowMain3() = runBlocking<Unit> {
    withTimeoutOrNull(250) { // Timeout after 250ms
        flowSimple3().collect { value -> println(value) }
    }
    println("Done")
}

//
//
//

fun flowSimple2(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun flowMain2() = runBlocking<Unit> {
    println("Calling simple function...")
    val flow = flowSimple2()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }
}

//
//
//

fun flowSimple1(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(1000) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}

fun flowMain1() = runBlocking<Unit> {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
        }
    }
    // Collect the flow
    flowSimple1().collect { value -> println(value) }
}
