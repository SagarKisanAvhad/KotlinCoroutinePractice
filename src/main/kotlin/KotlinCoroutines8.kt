import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts: ${Thread.currentThread().name}")
        try {
            withTimeout(2000) {
                println("Coroutine starts on thread: ${Thread.currentThread().name}")
                for (i in 1..100) {
                    print("$i. ")
                    delay(100)
                }
            }
        } catch (ex: TimeoutCancellationException) {
            println("\nCatch executing: ${ex.localizedMessage}")
        } finally {
            println("\nfinally executing")
        }


    println("\nMain ends:${Thread.currentThread().name}")
}
