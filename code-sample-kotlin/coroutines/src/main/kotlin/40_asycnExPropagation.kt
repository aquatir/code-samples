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
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() = runBlocking {

    val res = coroutineScope {

        try {
            val a = async { method1() }
            val b = async { method2() }

            a.await() + b.await()
        } catch (ex: Exception) {
            println("error!") // this ex handler will never be called, because ex in method2 will cancel whole coroutineScope. It is a design decision and it's here to stay
            10
        }


    }

    println(res)
}

fun method1(): Int = 1
fun method2(): Int {
    throw RuntimeException()
}
