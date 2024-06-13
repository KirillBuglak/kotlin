package concurrency

import java.util.concurrent.CompletableFuture
import java.util.concurrent.CountDownLatch
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.Volatile
import kotlin.concurrent.thread
import kotlin.concurrent.withLock

@Volatile var i = 1
object Monitor
val lock = ReentrantLock()
val latch = CountDownLatch(3)
val executorService = Executors.newFixedThreadPool(5)
val barrier = CyclicBarrier(3)

fun main() {
//    simpleThreadTries()
//    interruptTries()
//    simpleExecutorExample()
//    monitorTries()
//    lockedTries()
//    withLockTries()
//    latchAndBarrierTries()
    futureTries()
}

private fun futureTries() {
    val function: () -> String = {
        Thread.sleep(1_000)
        "greg"
    }
    val future = executorService.submit(function)
    println(future.isDone)
    Thread.sleep(1_100)
    println(future.isDone)
    println(future.get())
    executorService.shutdown()

    CompletableFuture.supplyAsync(function).thenApplyAsync { System.err.println("Completed") }.get()
}

private fun latchAndBarrierTries() {
    for (i in 1..3) {
        executorService.submit {
            println("Counted - $i")
            latch.countDown()
            Thread.sleep(1_000)
            barrier.await()
            System.err.println("Awaited barrier")
        }
    }
    executorService.shutdown()
    latch.await()
    System.err.println("Finished")
}

private fun withLockTries() {
    thread(start = true, name = "first") {
        withLock()
    }
    thread(start = true, name = "second") {
        withLock()
    }
}

private fun lockedTries() {
    thread(start = true, name = "first") {
        locked()
    }
    thread(start = true, name = "second") {
        locked()
    }
}

private fun monitorTries() {
    thread(start = true, name = "first") {
        Thread.sleep(100)
        sync()
    }
    thread(start = true, name = "second") {
        Thread.sleep(200)
        sync()
    }
}

private fun locked () {
    if (lock.tryLock(1, TimeUnit.SECONDS)) {
        println("got this lock")
        Thread.sleep(3_000)
        lock.unlock()
    } else {
        System.err.println("No lock")
    }
}

private fun withLock () {
    lock.withLock { //todo use the lock and have it automatically released
        println("got this lock")
        Thread.sleep(1_000)
    }
}

private fun sync() {
    synchronized(Monitor) {
        println("${Thread.currentThread().name} got monitor")
    }
}

private fun simpleExecutorExample() {
    executorService.submit { print("Greg") }
    executorService.shutdownNow()
}

private fun interruptTries() {
    val thr1 = thread(start = true, name = "SomeThread") {
        while (!Thread.interrupted()) {
            println("Hey man - $i")
            i++
        }
        System.err.println("Interrupted")
    }

    thread(start = true, name = "SomeThread") {
        Thread.sleep(3_000)
        thr1.interrupt()
    }
}

private fun simpleThreadTries() {
    thread(start = true, name = "SomeThread") {
        for (i in 1..3) {
            println("Hey")
        }
    }
}