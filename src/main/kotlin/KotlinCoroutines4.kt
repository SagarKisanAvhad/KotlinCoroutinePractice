import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")
    val deferredJob:Deferred<String> = async { //coroutine created on background thread T1
        println("Fake work starts: ${Thread.currentThread().name}") //T1
        println("Fake work ends: ${Thread.currentThread().name}")//T1 or other Thread
        delay(1000)//T1 free
    "Sagar Kisan Avhad"
    }
    val value = deferredJob.await()
    println("Main ends:$value ${Thread.currentThread().name}")
}

private suspend fun mySuspendFunction(time: Long) {
    delay(time)
}
