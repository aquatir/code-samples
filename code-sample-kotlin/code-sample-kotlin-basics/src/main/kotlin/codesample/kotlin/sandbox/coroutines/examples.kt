package codesample.kotlin.sandbox.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlin.random.Random

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
fun main() =
    runBlocking {
        GlobalScope.launch {
            delay(50L)
            println("World!")
        }
        print("Hello, ")
        Thread.sleep(150L)


        // Channels!
        val intChannel = Channel<Int>()
        launch {
            for (x in 1..5) intChannel.send(x * 3)
        }

        repeat(5) {
            val valueReceived = intChannel.receive()
            print("$valueReceived ")
        }
        println()

        // Easier-to-use channels
        val producer = produce { for (x in 1..5) send(x * 2) }
        producer.consumeEach { print("$it ") }
        println()

        // Infinite stream of ints (with cancel)
        val infiniteProducer = produce {
            var x = 0
            while (true) send(x++)
        }

        for (i in 0..10) {
            val value = infiniteProducer.receive()
            print("$value ")
        }
        println()
        coroutineContext.cancelChildren()

        println("****************")

        // Prime filter
        var cur = numbersFrom(2)
        for (i in 1..10) {
            val prime = cur.receive()
            println(prime)

            // swap current filter for one without this prime or numbers divided by this prime (aka Sieve of Eratosthenes algorithm)
            cur = filter(cur, prime)
        }
        coroutineContext.cancelChildren()

        println("****************")

        // Fan-out example
        val numProducer = produce {
            var x = 1 // start from 1
            while (true) {
                send(x++) // produce next
                delay(50) // wait 0.1s
            }
        }

        repeat(5) {
            launch {
                val id = Random.nextInt()
                for (msg in numProducer) {
                    println("consumerId: $id message: $msg")
                }
            }
        }

        delay(1000)
        numProducer.cancel()


        println("****************")

        // Ticker channel
        val ticker = ticker(delayMillis = 1000, initialDelayMillis = 0)

        repeat(3) {
            ticker.receive()
            println("ping")
            ticker.receive()
            println("pong")
        }
        ticker.cancel()
        println("****************")


    }



fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    var x = start
    while (true) send(x++) // infinite stream of integers from start
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
    for (x in numbers) {
        if (x % prime != 0) {
            send(x)
        }
    }
}