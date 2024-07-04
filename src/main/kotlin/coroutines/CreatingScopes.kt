package coroutines

import kotlinx.coroutines.*

fun main() {
//    someScopeTries()
//    println(namedScope().coroutineContext)
    cancellingScope()
}

private fun cancellingScope() {
    val someScope = getSomeScope()
    someScope.launch {
        println("Before cancellation")
        delay(1_000)
        println("Hey form custom scope")
    }
    GlobalScope.launch(Dispatchers.Main) {
        delay(500)
        someScope.cancel()
    }
}

private fun namedScope(): CoroutineScope {
    return CoroutineScope(Dispatchers.Main + CoroutineName("Named"))
}

private fun someScopeTries() {
    getSomeScope().launch {
        println("Hey form custom scope")
    }
}

private fun getSomeScope(): CoroutineScope {
    return CoroutineScope(Dispatchers.Main)
}