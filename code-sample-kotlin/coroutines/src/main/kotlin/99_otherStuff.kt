import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis



suspend fun maybeSuccess(): Boolean = coroutineScope {
    val result = Random.nextInt(10)
    log("is $result == 0 ? ")
    if (result == 0) true else throw IllegalArgumentException("kek")
}

fun main3() = runBlocking {

    val flowRes = flow {
        emit(maybeSuccess())
    }
            .flowOn(Dispatchers.Default)
            .retryWhen { _, _ -> true } // retry forever!

    val fst = flowRes.firstOrNull()
    log("done $fst")
}

// Read only first emitted even and cancel all others
fun main1() = runBlocking {

    val time = measureTimeMillis {

        // Can not do the same of normal flow as it can not emit from different coroutines
        val flw = channelFlow<Int> {
            for (i in 3 downTo 1) {
                launch {
                    log("first elem delay: '${i * 1000}' value: '${i * 2}'")
                    delay(i * 1000L)
                    channel.offer(i * 2)
                }
            }
        }

        println("fst: ${flw.first()}")
    }

    log("time: $time")
}


fun main2() = runBlocking {

    val time = measureTimeMillis {

        // Can not do the same of normal flow as it can not emit from different coroutines
        val flw = flow<Int> {
            for (i in 3 downTo 1) {
                log("first elem delay: '${i * 1000}' value: '${i * 2}'")
                delay(i * 1000L)
                emit(i * 2)
            }
        }.flowOn(Dispatchers.Default)

        log("fst: ${flw.first()}")
    }

    log("time: $time")
}

