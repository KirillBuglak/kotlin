package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

@OptIn(DelicateCoroutinesApi::class)
fun main() {
//    simpleChannelExample()
//    factoryChannelCreation()
//    sendAndReceiveCahnnels()
    sendAndTrySendTries()
}

private fun sendAndTrySendTries() {
    val channel = Channel<Int>(Channel.BUFFERED)
    val channel2 = Channel<Int>(Channel.BUFFERED)
    for (i in 1 until 66) {
        println(channel.trySend(i).isSuccess)
    }
    runBlocking {
        try {
            withTimeout(2_000) {
                for (i in 1 until 66) {
                    channel2.send(i)
                    println("Sent - $i")
                }
            }
        } finally {
            System.err.println("Time is up")
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun sendAndReceiveCahnnels() {
    GlobalScope.launch(Dispatchers.Main) {
        val receiveChannel = produce {
            for (i in 11..20) {
                delay(100)
                send(i)
            }
        }
        receiveChannel.consumeEach(::println)
    }
    GlobalScope.launch(Dispatchers.Main) {
        val sendChannel = actor<Int> {
            consumeEach(::println)
        }
        for (i in 1..10) {
            sendChannel.send(i)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun factoryChannelCreation() {
    val channel = Channel<Int>(Channel.BUFFERED)

    GlobalScope.launch(Dispatchers.Default) {
        for (i in 1..10) {
            delay(100)
            channel.send(i)
        }
        channel.close()
    }

    GlobalScope.launch(Dispatchers.Main) {
        channel.consumeEach(::println)
    }
}

private fun simpleChannelExample() {
    runBlocking {
        val channel = produce {
            for (i in 1..10) {
                send(i)
            }
        }
        channel.consumeEach(::println)
    }
}