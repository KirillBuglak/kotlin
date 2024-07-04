package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
//    simpleChannelExample()
//    factoryChannelCreation()
//    sendAndReceiveCahnnels()
//    sendAndTrySendTries()
//    pollingTries()
//    toListTries()
//    consumeAsFlowTries()
//    bufferedTries()
//    conflatedTries()
//    multipleConsumersTries()
    broadcastTries()

}

private fun broadcastTries() { //todo Replaced with shared or state flows
    val flow = MutableSharedFlow<Int>(
        3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        extraBufferCapacity = 1
    )

    GlobalScope.launch(Dispatchers.Main) {
        flow.collect{ println("A - $it") }
    }

    GlobalScope.launch(Dispatchers.Main) {
        flow.collect{ println("B - $it") }
    }

    GlobalScope.launch(Dispatchers.Default) {
        for (i in 1..10) {
            flow.tryEmit(i)
        }
    }
}

private fun multipleConsumersTries() {
    val channel = Channel<Int>(Channel.BUFFERED)

    GlobalScope.launch(Dispatchers.Main) {
        channel.consumeEach { println("A - $it") }
    }

    GlobalScope.launch(Dispatchers.Main) {
        channel.consumeEach { println("B - $it") }
    }

    GlobalScope.launch(Dispatchers.Default) {
        for (i in 1..10) {
            channel.send(i)
        }
    }
}

private fun conflatedTries() {
    val channel = Channel<Int>(Channel.CONFLATED)
    GlobalScope.launch(Dispatchers.Main) {
        delay(1_000)
        channel.consume(::println)
    }

    GlobalScope.launch(Dispatchers.Default) {
        for (i in 1..50) {
            delay(100)
            channel.send(i)
            println("Sent to channel - $i")
        }
    }
}

private fun bufferedTries() {
    val channel = Channel<Int>(capacity = 3)
    GlobalScope.launch(Dispatchers.Main) {
        delay(1_000)
        channel.consumeEach(::println)
    }

    GlobalScope.launch(Dispatchers.Default) {
        for (i in 1..5) {
            channel.send(i)
            println("Sent to channel - $i")
        }
    }
}

private fun consumeAsFlowTries() {
    val channel = Channel<Int>(Channel.BUFFERED)
    GlobalScope.launch(Dispatchers.Default) {
        for (i in 1..6) {
            delay(200)
            channel.send(i)
        }
        channel.close()
    }
    runBlocking {
        channel.consumeAsFlow().collect(::println)
    }
}

private fun toListTries() {
    val channel = Channel<Int>(Channel.BUFFERED)
    GlobalScope.launch(Dispatchers.Default) {
        for (i in 1..6) {
            delay(200)
            channel.send(i)
        }
        channel.close()
    }
    runBlocking {
        println(channel.toList())
    }
}

private fun pollingTries() {
    val channel = Channel<Int>(Channel.BUFFERED)
    GlobalScope.launch(Dispatchers.Default) {
        for (i in 1..6) {
            delay(200)
            channel.send(i)
        }
        channel.close()
    }
    println(channel.tryReceive().getOrNull())
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