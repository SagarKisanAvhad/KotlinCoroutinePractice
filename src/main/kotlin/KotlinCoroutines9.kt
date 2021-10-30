import kotlinx.coroutines.*

fun main() = runBlocking(Dispatchers.Default) {
    println("Main starts: ${Thread.currentThread().name}")

    val value:String? = withTimeoutOrNull(2000){
        println("Coroutine starts on thread: ${Thread.currentThread().name}")
        for (i in 1..10){
            print("$i. ")
            delay(100)
        }
        println("Coroutine ends on thread: ${Thread.currentThread().name}")
        "Build And Implement"
    }
    println("\nValue:$value")
    println("\nMain ends:${Thread.currentThread().name}")
}
