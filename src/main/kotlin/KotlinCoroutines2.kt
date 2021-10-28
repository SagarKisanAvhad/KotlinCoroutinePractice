import kotlinx.coroutines.*

fun main() = runBlocking{ //create coroutine that blocks main thread
        println("Main starts: ${Thread.currentThread().name}")
        GlobalScope.launch { //coroutine created on background thread T1
            println("Fake work starts: ${Thread.currentThread().name}") //T1
            delay(1000)//T1 free
            println("Fake work ends: ${Thread.currentThread().name}")//T1 or other Thread
        }
        delay(2000)
        println("Main ends: ${Thread.currentThread().name}")
}