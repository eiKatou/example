import kotlinx.coroutines.*
import kotlin.concurrent.thread

fun main(args: Array<String>) {
//    globalScopeLaunch()
//    thread()
//    runBlocking()
//    runBlocking2()
//    join()
//    structuredConcurrency()
//    scopeBuilder()
//    suspendMain()
//    lightWeight()
    likeDaemonThreads()
}

fun likeDaemonThreads() = runBlocking {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // just quit after delay
}

fun lightWeight() = runBlocking {
    repeat(100_000) { // launch a lot of coroutines
        launch {
            delay(1000L)
            print(".")
        }
    }
}

fun suspendMain() = runBlocking {
    launch { doWorld() }
    println("Hello,")
}

// this is your first suspending function
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

fun scopeBuilder() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope { // Creates a coroutine scope
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before the nested launch
    }

    println("Coroutine scope is over") // This line is not printed until the nested launch completes
}

fun structuredConcurrency() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine in the scope of runBlocking
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

fun join() = runBlocking {
    val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // wait until child coroutine completes
}

fun runBlocking2() = runBlocking<Unit> { // start main coroutine
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main coroutine continues here immediately
    delay(2000L)      // delaying for 2 seconds to keep JVM alive
}

fun runBlocking() {
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main thread continues here immediately
    runBlocking {     // but this expression blocks the main thread
        delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
    }
}

fun globalScopeLaunch() {
    GlobalScope.launch { // バックグラウンドで新しいコルーチンを起動し、続行する
        delay(1000L) // 1秒間ノンブロッキング遅延 (デフォルトの時間単位はms)
        println("World!") // delayのあとでプリント
    }
    println("Hello,") // コルーチンが遅延している間、メインスレッドは継続する
    Thread.sleep(2000L) // メインスレッドを2秒間ブロックしてJVMを存続させます
}

fun thread() {
    thread { // バックグラウンドで新しいコルーチンを起動し、続行する
        Thread.sleep(1000L) // 1秒間ノンブロッキング遅延 (デフォルトの時間単位はms)
//        delay(1000L)
        println("World!") // delayのあとでプリント
    }
    println("Hello,") // コルーチンが遅延している間、メインスレッドは継続する
    Thread.sleep(2000L) // メインスレッドを2秒間ブロックしてJVMを存続させます
}