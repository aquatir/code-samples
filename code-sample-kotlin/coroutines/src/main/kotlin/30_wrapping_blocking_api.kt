import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val blockingApiProvider = BlockingApiProvider()
    val blockingApiWrapper = BlockingApiWrapper(blockingApiProvider)

    val jobs = mutableListOf<Job>()

    val time = measureTimeMillis {
        val outerJob = launch {
            for (i in 1..50) {
                jobs.add(
                        launch(Dispatchers.Default) {
                            val res = blockingApiWrapper.makeBlockingCall()
                            println("done with $i")
                        }
                )
            }
        }
        outerJob.join()
    }

    // WHY ARE YOU NOT FINISHING NORMALLY?!
    blockingApiWrapper.stop()
    coroutineContext.cancelChildren()
    println("time taken: $time")
}

class BlockingApiWrapper(private val blockingApiProvider: BlockingApiProvider) {
    private val scope = CoroutineScope(Executors.newFixedThreadPool(50).asCoroutineDispatcher())

    suspend fun makeBlockingCall(): Int {
        return withContext(scope.coroutineContext) {
            blockingApiProvider.makeBlockingCall()
        }
    }

    fun stop() {
        scope.cancel()
    }
}

class BlockingApiProvider() {
    fun makeBlockingCall(): Int {
        // emulate blocking call
        Thread.sleep(5000)
        return 1
    }
}
