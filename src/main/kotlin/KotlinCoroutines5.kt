import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")
    val job = launch(Dispatchers.Default) { //coroutine created on background thread T1
        for (i in 0..500){
            println("$i.")
            //cooperative suspending function
            delay(50) //yield()
        }
        /*for (i in 0..500){
            //using flag with non-cooperative
            if (!isActive)
                break
            println("$i.")
            Thread.sleep(1)
        }*/
    }
    delay(10)
    job.cancelAndJoin()
    println("\nMain ends:${Thread.currentThread().name}")
}
