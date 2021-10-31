import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts")
    println("$this")
    println("$coroutineContext")
    launch(context = CoroutineName("CoroutineName1") + Dispatchers.Default) {
        println("$this")
        println("$coroutineContext")
    }
    async(CoroutineName("CoroutineName2")+Dispatchers.Unconfined) {
        println("$this")
        println("$coroutineContext")
    }
    println("Main ends")
}