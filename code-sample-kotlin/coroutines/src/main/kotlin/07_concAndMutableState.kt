import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

// Message types for counterActor
sealed class CounterMsg
object IncCounter : CounterMsg() // one-way message to increment counter
class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg() // a request with reply

// This function launches a new counter actor
fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0 // actor state
    for (msg in channel) { // iterate over incoming messages
        when (msg) {
            is IncCounter -> counter++
            is GetCounter -> msg.response.complete(counter)
        }
    }
}


val counterContext = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
val mutex = Mutex()
val counter = AtomicInteger()

fun mainConcAndMutableState() = runBlocking {

    val counterActor = counterActor()
    withContext(Dispatchers.Default) {
        //mutex.withLock {
            massiveRun {
                counterActor.send(IncCounter)
            }
        //}
    }
    val response = CompletableDeferred<Int>()
    counterActor.send(GetCounter(response))
    println("Counter = ${response.await()}")
    counterActor.close()

    println()
    //println("Counter = $counter")
}

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100  // number of coroutines to launch
    val k = 1000 // times an action is repeated by each coroutine
    val time = measureTimeMillis {
        coroutineScope { // scope for coroutines
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}
