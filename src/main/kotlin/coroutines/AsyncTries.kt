package coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.coroutineContext

@OptIn(DelicateCoroutinesApi::class)
fun main() {
//    asyncTries()
//    runBlockingTries()
//    sameDispatcherTries()
//    asCoroutineDispatcherTries()

}

@OptIn(DelicateCoroutinesApi::class)
private fun asCoroutineDispatcherTries() {
    val executorService = Executors.newFixedThreadPool(5)
    GlobalScope.launch(executorService.asCoroutineDispatcher()) {
        for (i in 1..5) {
            println("Hey")
//            delay(1_000)
        }
    }
    executorService.shutdown()
}

@OptIn(DelicateCoroutinesApi::class)
private fun sameDispatcherTries() {
    GlobalScope.launch(Dispatchers.Main) {
//    GlobalScope.launch { //todo same as .launch(Dispatchers.Default)
        theSameDispatcher()
    }
}

private suspend fun theSameDispatcher() {
    coroutineScope {
        println("Same dispatcher")
        delay(1_000)
        println("Finish")
    }
}

private fun runBlockingTries() {
    runBlocking { //todo allows to call suspend funs in non-suspend funs
        someLogic()
    }

    println("Finish!")
}

private suspend fun someLogic() {
    System.err.println("Blocking logic")
    delay(1_000)
    println("Finished blocking")
}

@OptIn(DelicateCoroutinesApi::class)
private fun asyncTries() {
    val async: Deferred<Int> = GlobalScope.async(Dispatchers.Default) {
        System.err.println("Some")
        delay(1_000)
        1234
    }

    GlobalScope.launch(Dispatchers.Main) {
        println(async.await()) //todo await is a blocking call
    }
}