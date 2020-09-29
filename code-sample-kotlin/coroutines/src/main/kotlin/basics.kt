import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    GlobalScope.launch {
        delay(1000)
        print(" World!")
    }

    print("Hello,")
    runBlocking {
        delay(2000)
    }
}
