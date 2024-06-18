package coroutines

import kotlinx.coroutines.*
import java.util.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
//    parentJobTries()
//    lazyJob()
//    cancelJob()
//    exceptionTries()
//    exceptionInChild()
//    timeoutTries()
//    cancellTries()
//    isActiveTries()
//    nonCancelableTries()
//    simpeCancelledTryFinallyBlock()
    val job = GlobalScope.launch(Dispatchers.Main) {
        delay(1_000)
    }
    GlobalScope.launch(Dispatchers.Main) {
        job.cancel()
    }
    job.invokeOnCompletion { cause -> println(cause) }
}

@OptIn(DelicateCoroutinesApi::class)
private fun simpeCancelledTryFinallyBlock() {
    val job = GlobalScope.launch(Dispatchers.Main) {
        try {
            delay(1000)
            println("first")
        } finally { //todo execute this block when cancelled
            println("Cancelled")
        }
    }
    GlobalScope.launch(Dispatchers.Main) {
        job.cancel()
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun nonCancelableTries() {
    val job = GlobalScope.launch(Dispatchers.Main) {
        try {
            delay(1000)
            println("first")
        } finally {
            withContext(NonCancellable) { //todo execute this block even when cancelled
                delay(1000)
                println("Cancelled")
            }
        }
    }
    GlobalScope.launch(Dispatchers.Main) {
        job.cancel()
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun isActiveTries() {
    GlobalScope.launch(Dispatchers.Main) {
        if (isActive) {
            println("first")
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun cancellTries() {
    GlobalScope.launch(Dispatchers.Main) {
        println("first")
        child1()
        println("Finish")
    }
}

private suspend fun child1() {
    withContext(Dispatchers.Unconfined) {
        println("second")
        child2(coroutineContext[Job]!!)
        println("third")
    }
}

private suspend fun child2(parent: Job) {
    withContext(Dispatchers.Unconfined) {
        println("fourth")
        parent.cancel()
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun timeoutTries() {
    GlobalScope.launch(Dispatchers.Main) {
        println("Hey")
//        withTimeout(100){ //todo triggers cancellation Exception - so we won't see message after timeout
//            longDelay()
//        }
//        println("never see this after timeout")
        val result = withTimeoutOrNull(1000) { //todo returns null if timeout occurs
            longDelay()
        }
        println("get this even after timeout")
    }
}

private suspend fun longDelay() {
    withContext(Dispatchers.Default) {
        delay(20_000)
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun exceptionInChild() {
    GlobalScope.launch(Dispatchers.Main) {
        println("First")
        GlobalScope.launch(Dispatchers.Unconfined) {
            2 / 0
        }
        hey()
        println("Second")
    }
}

private suspend fun hey() {
    withContext(Dispatchers.Unconfined) {
        2 / 0 //todo cancelling parent
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun exceptionTries() {
    GlobalScope.launch(Dispatchers.Main) {
        println("First")
        2 / 0
        println("Fourth")
    }
    GlobalScope.launch(Dispatchers.Main) {
        println("Second")
        delay(1_000)
        println("Third")
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun cancelJob() {
    val job = GlobalScope.launch(Dispatchers.Unconfined) {
        println("First")
        delay(1_000)
        println("Fourth")
    }
    GlobalScope.launch(Dispatchers.Main) {
        println("Second")
        job.cancelAndJoin() //todo cancels the job and wait's for it cancellation to finish
//        job.cancel() //todo cancels the job
        println("Third")
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun lazyJob() {
    val job = GlobalScope.launch(Dispatchers.Main, start = CoroutineStart.LAZY) {
        println("Some")
    }
    println("Main job - $job")
    job.start()
    println("After start job - $job")
}

@OptIn(DelicateCoroutinesApi::class)
private fun parentJobTries() {
    val job = GlobalScope.launch(Dispatchers.Main) {
        println("job before - ${coroutineContext[Job]}")
        getJobs(coroutineContext[Job]!!)
        println("job after - ${coroutineContext[Job]}")
    }
    println("Main job - $job")
}

suspend fun getJobs(parent: Job) {
    withContext(Dispatchers.Unconfined) {
        println("Parent's children job - ${parent.children.joinToString(", ")}")
        println("Parent's job - $parent")
        println("inside context job - ${coroutineContext[Job]}")
    }
}