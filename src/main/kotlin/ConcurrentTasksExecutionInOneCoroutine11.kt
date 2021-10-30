import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    println("Main starts: ${Thread.currentThread().name}")

    /*//using launch
   val totalTimeTaken = measureTimeMillis {
        val result1 = launch{longRunningTaskOne()}
        val result2 = launch {longRunningTaskTwo()}
       result1.join()
       result2.join()
       // println("${result1.await()} ${result2.await()}")
    }
    println("Total time taken: $totalTimeTaken ms")*/

    //using async
    val totalTimeTaken = measureTimeMillis {
        val result1 = async{longRunningTaskOne()}
        val result2 = async {longRunningTaskTwo()}
        println("${result1.await()} ${result2.await()}")
    }
    println("Total time taken: $totalTimeTaken ms")

    println("Main ends: ${Thread.currentThread().name}")
    }

suspend fun longRunningTaskOne():String{
    delay(1000) //pretending
    return "Hello"
}

suspend fun longRunningTaskTwo():String{
    delay(1000)  //pretending
    return "Nerd"
}
