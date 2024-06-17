package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.flow

@OptIn(DelicateCoroutinesApi::class)
fun main() {
//    simpleTry()
    GlobalScope.launch(Dispatchers.Main) {
        val channelTrySendExample = channelTrySendExample()
        val channelSendExample = channelSendExample()
        val flowExample = flowExample()
        delay(4000)
        flowExample.collect { println(it) } //todo blocked before 'collect'
//        channelSendExample.consumeEach { println(it) } //todo starts emitting only after 'consumeEach'
//        channelTrySendExample.consumeEach { println(it) } //todo starts emitting at initialization
    }
}

fun CoroutineScope.channelSendExample() = produce {//todo sends all elements
    println("Start channelSendExample")
    for (i in 1..10) {
        delay(500)
        send(i) //todo like return
    }
}

fun CoroutineScope.channelTrySendExample() = produce {//todo sends all elements
    println("Start channelTrySendExample")
    for (i in 1..10) {
        delay(500)
        trySend(i) //todo like return
    }
}

fun flowExample() = flow { //todo doesn't emit all elements
    println("Start flowExample")
    for (i in 1..10) {
        delay(500)
        emit(i) //todo like return
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun simpleTry() {
    GlobalScope.launch(Dispatchers.Main) {
        println("Before")
        delaying()
        println("After")
    }
    GlobalScope.launch(Dispatchers.Main) {
        println("Before2")
        delaying()
        println("After2")
    }
    println("Immediate")
}

//suspend fun main() { //todo same version of main above
//        println("Before")
//        delaying()
//        println("After")
//}

suspend fun delaying() {
    withContext(Dispatchers.Unconfined) {
        delay(1_000)
    }
}

