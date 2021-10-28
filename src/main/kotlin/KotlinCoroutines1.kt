import kotlin.concurrent.thread

fun main(){ // Execute in main thread
    println("Main starts: ${Thread.currentThread().name}")
    thread { //background thread created
        Thread.sleep(1000)
        println("Fake work starts: ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("Fake work ends: ${Thread.currentThread().name}")
    }
    println("Main ends: ${Thread.currentThread().name}")
}