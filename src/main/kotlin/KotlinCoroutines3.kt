import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")
    val job = launch { //coroutine created on background thread T1
        println("Fake work starts: ${Thread.currentThread().name}") //T1
        delay(1000)//T1 free
        println("Fake work ends: ${Thread.currentThread().name}")//T1 or other Thread
    }
    job.join()
    println("Main ends: ${Thread.currentThread().name}")
}

private suspend fun mySuspendFunction(time: Long) {
    delay(time)
}
