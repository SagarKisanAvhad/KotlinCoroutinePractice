# Basic Coroutine Notepoint:


======Thread and Coroutine========

1. We don’t perform long running task on Main thread as it is UI thread. We used to do long running operations on background threads. This is not a recommended solution as we end up with many background threads in app. Threads are expensive. We might get OutOfMemoryError.
2. We create coroutines for each long running operations. We can create as many as coroutines in a single thread. They are very cheap. We make use of thread efficiently.
3. Application waits for thread to complete its operation whereas Application doesn’t wait for Coroutine to complete its operation.


======Coroutine and its builders========

<img width="1522" alt="Screenshot 2023-03-07 at 9 50 29 PM" src="https://user-images.githubusercontent.com/29921265/223483772-492a07d5-c33b-4604-8f51-05992a06d76d.png">

1. Coroutine builders are use for creating coroutine. There are 3 ways to create coroutine: launch, async , runBlocking
2. Similarity in launch and async is they create coroutine that doesn’t block thread.
3. runBlocking create coroutine that block thread. It is use when we need to call suspend function from main thread and  while testing suspend function.
4. Always remember two things regarding coroutine, thread on which it is executing and coroutineScope
5. launch and async inherits parent coroutine thread and coroutineScope
6. runBlocking creates coroutine on caller thread.
7. When we use GlobalScope with launch/async for creating coroutine then our coroutine will have global scope means App level. This is the easiest way to launch coroutine but only use when needed.
8. GlobalScope we can use for long running operation that doesn’t bounded to particular screen. example, multiple images sharing on WhatsApp. User should able to navigate any screen in WhatsApp.
9. launch is fire and forgot . It returns job for controlling coroutine but doesn’t have return value from block
10. Async returns Deferred job that has value. 
11.  runBlocking doesn’t returns Job. It returns last line value in block


======Coroutine Cancellation===

1. We can cancel coroutine if its cooperative. Cooperative means suspending functions that are in kotlin.coroutine package.
2. For cancelling non-cooperative coroutine, we can use ensureActive() or yield().<br>
<img width="961" alt="Screenshot 2023-03-07 at 10 18 21 PM" src="https://user-images.githubusercontent.com/29921265/223491872-25854888-3550-440b-9e1d-7cc835ebb7fd.png">

3. If we cancel, cancellable coroutine then cooperative suspend function throws CancellationException
4. We can not run suspend function from finally block, because Coroutine running this code is already cancelled. Still if we want to run suspend function in finally block then wrap the code within withContext(NonCancellable) function
5. withContext() is used for changing coroutine context. it makes main safe suspend function.
6. withTimeout() and withTimeoutNull() are also coroutine builder. Both usages for time bounded coroutine operation. Both return lambda result. withTimeout() throws TimeoutCancellationException on timeout.


========== Unit testing coroutines =================<br>
**USE CASE 1: For testing suspend function**<br>
<img width="950" alt="Screenshot 2023-03-07 at 10 23 50 PM" src="https://user-images.githubusercontent.com/29921265/223492639-62a30ee8-5d77-4631-ab80-c852fba5f62d.png">
<img width="1353" alt="Screenshot 2023-03-07 at 10 24 57 PM" src="https://user-images.githubusercontent.com/29921265/223492995-ad528fb4-e2e3-4448-a524-f3ce79d5a655.png">

**USE CASE 2: For testing a function that trigger a coroutine**<br>
<img width="911" alt="Screenshot 2023-03-07 at 10 26 13 PM" src="https://user-images.githubusercontent.com/29921265/223493268-3961c4f8-152b-4c8f-8b61-8f3194e30a16.png">
<img width="1637" alt="Screenshot 2023-03-07 at 10 27 57 PM" src="https://user-images.githubusercontent.com/29921265/223493779-6d53ce18-4cdf-4e19-8f5d-17b2c0657a34.png">

**USE CASE 3: For testing a set of instruction before triggering a coroutine.**<br>
for testing *//Do something else* in below image
<img width="1203" alt="Screenshot 2023-03-07 at 10 29 38 PM" src="https://user-images.githubusercontent.com/29921265/223494934-51c73a10-8ad1-40d5-86b6-4e9673ccd8b4.png">
<img width="1645" alt="Screenshot 2023-03-07 at 10 33 38 PM" src="https://user-images.githubusercontent.com/29921265/223495144-7a17fde3-7394-4c02-beab-d559f29f8ef7.png">

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
1. What problem does coroutine solve?<br>
Ans: It simplify asynchronous programing. we dont need to write callback. kotlin internally writes callback.<br>
2. What is coroutine?<br>
Ans: Runnable with superpowers. it takes a block of code and run it on a perticular thread. other perks like cancellation and exception handling. Cancellation and exception handling are very difficult in other versions of asynchronous programing, for example callback.<br>
3. What is main safe function?<br>
Ans: suspend function that take responsibility for switching the thread. it doesn't rely on caller side. use withContext() for changing thread.<br>
<img width="771" alt="Screenshot 2023-03-07 at 9 18 51 PM" src="https://user-images.githubusercontent.com/29921265/223474408-a1925e74-05bd-40ee-a98a-f1f21f23b0c8.png">
4. What types of dispatchers in coroutine?<br>
Ans:<br>
<img width="606" alt="Screenshot 2023-03-07 at 9 23 24 PM" src="https://user-images.githubusercontent.com/29921265/223476187-d85d56f3-9197-4ea5-afd9-70ea1bcf69d0.png">




===============Good coroutine articles references=======
<img width="776" alt="Screenshot 2023-03-07 at 10 38 35 PM" src="https://user-images.githubusercontent.com/29921265/223496604-554a7dea-f31d-4135-b582-afda4fda2b66.png">

1) [Coroutines under the hood](https://kt.academy/article/cc-under-the-hood) - Kt Academy
2) [How does suspension work in Kotlin coroutines?](https://kt.academy/article/cc-suspension) - Kt Academy
3) [Suspend function under the hood simple article](https://betterprogramming.pub/kotlin-coroutine-internals-49518ecf2977) - shared by Athul Antony
4) [Flow, StateFlow, shared flow testing emitting and collecting side](https://codingwithmohit.com/coroutines/learning-shared-and-state-flows-with-tests/) - coding with mohit. After 2 days struggle, finally found.
5) [Kotlin coroutine Use cases example](https://github.com/LukasLechnerDev/Kotlin-Coroutine-Use-Cases-on-Android) - LukasLechnerDev GitHub
6) [Kotlin coroutine](https://magdamiu.medium.com/kotlin-coroutines-7938b5c7a11b)- medium article by Magda Miu
7) [Android developer medium](https://medium.com/androiddevelopers/coroutines-on-android-part-i-getting-the-background-3e0e54d20bb)- Neeraj shared
8) [Android developer medium](https://medium.com/androiddevelopers/exceptions-in-coroutines-ce8da1ec060c)- Neeraj shared
9) [Roman](https://elizarov.medium.com/?source=post_page-----f2cc748b2401--------------------------------) - Neeraj shared
10) [Asynchronous programming](https://www.bmc.com/blogs/asynchronous-programming/) - beautiful blog by bmc. general concept.

Find this project useful?:heart:
- Support it by clicking the ⭐ button on the upper right of this page. :v:

