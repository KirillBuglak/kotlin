package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
//    someFlowTries()
//    catchAndRetry()u
//    mutableSharedFlowTries()
//    mutableStatesFlowWithDelayedSubscr()
    val mutableStateFlow = MutableStateFlow("a")
    GlobalScope.launch(Dispatchers.Main) {
        for (i in 1..20) { //todo no matter how more cycles we add - there would be only one state change - the rest of instantiation is ignored since values are the same - 'b'
            delay(200)
            mutableStateFlow.value = "b"
        }
    }
    GlobalScope.launch(Dispatchers.Main) {
        mutableStateFlow.collect { //todo gets only a current state
            println(it)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun mutableStatesFlowWithDelayedSubscr() {
    val mutableStateFlow = MutableStateFlow("a")
    GlobalScope.launch(Dispatchers.Main) {
        for (i in 1..20) {
            delay(200)
            mutableStateFlow.value += i
        }
    }
    GlobalScope.launch(Dispatchers.Main) {
        mutableStateFlow.collect { //todo gets only a current state
            delay(500)
            println(it)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun mutableSharedFlowTries() {
    val mutableStateFlow = MutableSharedFlow<Int>(
        3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        extraBufferCapacity = 1
    ) //todo replay sets a number of item to replay for a new subscriber
    GlobalScope.launch(Dispatchers.Main) {
        for (i in 1..20) {
            delay(100)
            println(mutableStateFlow.tryEmit(i)) //todo checks if the item could be emitted
        }
    }
    GlobalScope.launch(Dispatchers.Main) {
        delay(1000)
        mutableStateFlow.collect(::println)
    }
    GlobalScope.launch(Dispatchers.Main) {
        delay(3000)
        mutableStateFlow.onStart { println("Started 3 items ago") }.collect(::println)
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun catchAndRetry() {
    GlobalScope.launch(Dispatchers.Main) {
        val myFlowWithExc = myFlowWithExc()
        myFlowWithExc
            .catch { println(it) }
            .collect(::println)
//        myFlowWithExc
//            .retry(3)
//            .collect(::println)
        myFlowWithExc
            .retryWhen { ex, attempt -> (attempt < 3) && (ex is ArithmeticException) } //todo specifying number of attempts and the exact exception
            .collect(::println)
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun someFlowTries() {
    val flowOf = flowOf(1, 2, 3, 4, 5)
    GlobalScope.launch(Dispatchers.Main) {
        val flow = myFlow()
        flow.collect { println(it) }
        flowOf.flowOn(Dispatchers.Default).collect(::println) //todo flowOn - to change Dispatcher
        println(flowOf.single()) //todo throws exception if the flow has more or less than a single element
    }
    val zipFlow = myFlow().onStart { println("Started some flow") }
        .filter { it % 2 == 0 }
        .onEach(::println)
        .map { it * 2 }
        .onEach(::println)
        .onCompletion { println("Completed") }.zip(flowOf) { i: Int, i2: Int -> "result is - " + (i + i2) }
    zipFlow.launchIn(GlobalScope)
//    runBlocking { zipFlow.collect(::println) }
}

fun myFlow() = flow {
    var int = 10
    repeat(10) { emit(int++) }
}

fun myFlowWithExc() = flow {
    var int = 10
    repeat(10) {
        emit(int++)
        2 / 0
    }
}