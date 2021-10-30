import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")
    val job = launch(Dispatchers.Default) { //coroutine created on background thread T1
        try {
            for (i in 0..500) {
                println("$i.")
                //cooperative suspending function
                delay(10) //yield()
                println("\npost exception code in try")
            }
        } catch (ex: CancellationException) {
            println("\nCatch executing: ${ex.localizedMessage}")
        } finally {
            withContext(NonCancellable) {
                delay(1000)
                println("\nfinally executing")
            }
        }
    }
    delay(40)
    job.cancel(CancellationException("My Own Message"))
    job.join()
    println("\nMain ends:${Thread.currentThread().name}")
}
