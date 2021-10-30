import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    println("Main starts: ${Thread.currentThread().name}")

   val totalTimeTaken = measureTimeMillis {
        val result1 = longRunningTask1()
        val result2 = longRunningTask2()
        println("$result1 $result2")
    }
    println("Total time taken: $totalTimeTaken ms")

    println("Main ends: ${Thread.currentThread().name}")
    }

suspend fun longRunningTask1():String{
    delay(1000) //pretending
    return "Hello"
}

suspend fun longRunningTask2():String{
    delay(1000)  //pretending
    return "Nerd"
}
