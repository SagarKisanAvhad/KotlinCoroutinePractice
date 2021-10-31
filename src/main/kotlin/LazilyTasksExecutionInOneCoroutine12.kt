import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    println("Main starts: ${Thread.currentThread().name}")
    //using async
        val result1 = async(start = CoroutineStart.LAZY){longRunningTaskFirst()}
        val result2 = async(start = CoroutineStart.LAZY){longRunningTaskSecond()}
        println("${result1.await()} ${result2.await()}")
    println("Main ends: ${Thread.currentThread().name}")
    }

suspend fun longRunningTaskFirst():String{
    delay(1000) //pretending
    println("After running task one")
    return "Hello"
}

suspend fun longRunningTaskSecond():String{
    delay(1000)  //pretending
    println("After running task two")
    return "Nerd"
}
