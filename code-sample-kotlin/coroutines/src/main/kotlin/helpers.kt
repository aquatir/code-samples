//  -Dkotlinx.coroutines.debug
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
