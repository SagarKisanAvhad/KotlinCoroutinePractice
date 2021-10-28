import kotlinx.coroutines.*

fun main(){ // Execute in main thread
    println("Main starts: ${Thread.currentThread().name}")
    GlobalScope.launch { //coroutine created on background thread
        Thread.sleep(1000)
        println("Fake work starts: ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("Fake work ends: ${Thread.currentThread().name}")
    }
    println("Main ends: ${Thread.currentThread().name}")
}