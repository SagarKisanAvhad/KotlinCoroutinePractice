# Basic Coroutine Notepoint:


======Thread and Coroutine========

1. We don’t perform long running task on Main thread as it is UI thread. We used to do long running operations on background threads. This is not a recommended solution as we end up with many background threads in app. Threads are expensive. We might get OutOfMemoryError.
2. We create coroutines for each long running operations. We can create as many as coroutines in a single thread. They are very cheap. We make use of thread efficiently.
3. Application waits for thread to complete its operation whereas Application doesn’t wait for Coroutine to complete its operation.


======Coroutine and its builders========

1. Coroutine builders are use for creating coroutine. There are 3 ways to create coroutine: launch, async , runBlocking
2. Similarity in launch and async is they create coroutine that doesn’t block thread.
3. runBlocking create coroutine that block thread. It is use when we need to call suspend function from main thread and  while testing suspend function.
4. Always remember two things regarding coroutine, thread on which it is working and coroutineScope
5. launch and async inherits parent coroutine thread and coroutineScope
6. runBlocking creates coroutine on caller thread.
7. When we use GlobalScope with launch/async for creating coroutine then our coroutine will have global scope means App level. This is the easiest way to launch coroutine but only use when needed.
8. GlobalScope we can use for long running operation that doesn’t bounded to particular screen. example, multiple images sharing on WhatsApp. User should able to navigate any screen in WhatsApp.
9. launch is fire and forgot . It returns job for controlling coroutine but doesn’t have return value from block
10. Async returns Deferred job that has value. 
11.  runBlocking doesn’t returns Job. It returns last line value in block


======Coroutine Cancellation===

1. We can cancel coroutine if its cooperative. Cooperative means suspending functions that are in kotlin.coroutine package.
2. For cancelling non-cooperative coroutine, we can use isActive flag.
3. If we cancel, cancellable coroutine then cooperative suspend function throws CancellationException
4. We can not run suspend function from finally block, because Coroutine running this code is already cancelled. Still if we want to run suspend function in finally block then wrap the code within withContext(NonCancellable) function
5. withContext() is used for changing coroutine context. it makes main safe suspend function.
6. withTimeout() and withTimeoutNull() are also coroutine builder. Both usages for time bounded coroutine operation. Both return lambda result. withTimeout() throws TimeoutCancellationException on timeout.


=======Composing Suspending functions(series, parallel and lazy)=================

1. By defaults, Coroutine works in sequentially.
2. For concurrent execution, create child coroutines for all our tasks.we can use async and launch
3. start = CoroutineStart.LAZY with async coroutine builder


=======Dispatchers, CoroutineContext and CoroutineScope================

1. Types of Coroutines: BlockingCoroutine, StandAloneCoroutine and DefferedCoroutine. These types come to know from CoroutineScope
2. CoroutineContext elements: Dispatchers(It decides threads), CoroutineName and Job

==========Thumb Rule for using Coroutine===============
1. Use 'withContext' when you do not need the parallel execution
2. Use 'async' only when you need the parallel execution
3. Both 'withContext' and 'async' can be used to get the result which is not possible with 'launch' 
4. Use 'withContext' to return the result of a single task
5. Use 'async' for results from multiple tasks that run in parallel

==========Coroutine notepoint from kotlin coroutine playlist in android developers youtube channel===============
1. What problem does coroutine solve?
Ans: It simplify asynchronous programing. we dont need to write callback. kotlin internally writes callback.
2. What is coroutine?
Ans:
3. What is main safe function
Ans: suspend function that take responsibility for switching the thread. it doesn't rely on caller side. use withContext() for changing thread.

===============Good coroutine articles references=======
1) [Coroutines under the hood](https://kt.academy/article/cc-under-the-hood) - Kt Academy
2) [How does suspension work in Kotlin coroutines?](https://kt.academy/article/cc-suspension) - Kt Academy
3) [Flow, StateFlow, shared flow testing emitting and collecting side](https://codingwithmohit.com/coroutines/learning-shared-and-state-flows-with-tests/) - coding with mohit. After 2 days struggle, finally found.
4) [Kotlin coroutine Use cases example](https://github.com/LukasLechnerDev/Kotlin-Coroutine-Use-Cases-on-Android) - LukasLechnerDev GitHub
5) [Kotlin coroutine](https://magdamiu.medium.com/kotlin-coroutines-7938b5c7a11b)- medium article by Magda Miu
6) [Android developer medium](https://medium.com/androiddevelopers/coroutines-on-android-part-i-getting-the-background-3e0e54d20bb)- Neeraj shared
7) [Android developer medium](https://medium.com/androiddevelopers/exceptions-in-coroutines-ce8da1ec060c)- Neeraj shared
8) [Roman](https://elizarov.medium.com/?source=post_page-----f2cc748b2401--------------------------------) - Neeraj shared
9) [Asynchronous programming](https://www.bmc.com/blogs/asynchronous-programming/) - beautiful blog by bmc. general concept.

Find this project useful?:heart:
- Support it by clicking the ⭐ button on the upper right of this page. :v:

