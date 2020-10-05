
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


// Read only first emitted even and cancel all others
fun main() = runBlocking {

    val time = measureTimeMillis {

        // Can not do the same of normal flow as it can not emit from different coroutines
        val flw = channelFlow<Int> {
            for (i in 3 downTo 1) {
                launch {
                    println("first elem delay: '${i * 1000}' value: '${i * 2}'")
                    delay(i * 1000L)
                    channel.offer(i * 2)
                }
            }
        }

        println("fst: ${flw.first()}")
    }

    println("time: $time")
}
