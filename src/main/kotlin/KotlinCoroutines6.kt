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
            println("\nCatch executing")
        } finally {
            delay(1000) // can't use suspend function in finally. it throws another exception
            println("\nfinally executing")// this line is not executed because above line throwing exception
            //refer KotlinCoroutines7 class for learning how to use suspend function in finally
        }
    }
    delay(40)
    job.cancelAndJoin()
    println("\nMain ends:${Thread.currentThread().name}")
}
