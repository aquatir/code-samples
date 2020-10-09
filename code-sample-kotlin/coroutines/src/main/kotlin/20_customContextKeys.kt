import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class MyLocalData(val param1: String, val param2: String): AbstractCoroutineContextElement(MyLocalData) {
    companion object Key: CoroutineContext.Key<MyLocalData>
}

fun mainCustomKey() = runBlocking {


    launch(Dispatchers.Default + MyLocalData("kek", "lol")) {

        val param1 = coroutineContext[MyLocalData]?.param1 ?: throw IllegalArgumentException("no param1")
        val param2 = coroutineContext[MyLocalData]?.param2 ?: throw IllegalArgumentException("no param2")

        println("param1: $param1, param2: $param2")
    }

    println()
}
