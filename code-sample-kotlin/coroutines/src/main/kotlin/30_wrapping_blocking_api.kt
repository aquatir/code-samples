import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val blockingApiProvider = BlockingApiProvider()
    val blockingApiWrapper = BlockingApiWrapper(blockingApiProvider)

    val jobs = mutableListOf<Job>()
    val jobsFinished = Array<Boolean>(50) { false }

    val time = measureTimeMillis {
        val outerJob = launch {
            for (i in 0..49) {
                jobs.add(
                        launch(Dispatchers.Default) {
                            blockingApiWrapper.makeBlockingCall()
                            println("done with $i")
                            jobsFinished[i] = true
                        }
                )
            }
        }
        outerJob.join()
    }

    println("all finished: ${jobsFinished.all { it }}")
    println("time taken: $time")
}

class BlockingApiWrapper(private val blockingApiProvider: BlockingApiProvider) {
    suspend fun makeBlockingCall() {
        return withContext(Dispatchers.IO) {// a thread-pool of 64 threads used here OR number of threads on machine if it is larger
            blockingApiProvider.makeBlockingCall()
        }
    }
}

class BlockingApiProvider() {
    fun makeBlockingCall() {
        // emulate blocking call
        Thread.sleep(5000)
    }
}
