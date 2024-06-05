package functions

import java.io.BufferedReader
import java.io.IOException
import java.io.Reader

fun main() {
//    applyTries()
//    letTries()
//    withTries()
//    runTries()
//    lazyTries()
//    useTries()
//    repeatTries()
    require_assert_checkTries("acrrrrrrdefg")
}

fun applyTries() { //todo apply returns the receiver object
    println(Thread().apply { isDaemon = true }.isDaemon) //todo apply returns the instance - so we can continue using Thread
//    Thread().setDaemon(true).start() todo can't do that since setDaemon returns void

    val array = arrayOf(1, 2, 3)
    array.apply { this[1] = 5 }.forEach { print(it) }
}

fun letTries() { //todo let returns the result of it
    val let = arrayOf(1, 2, 3).let {
        System.err.println(it[1])
        it.size }
    println(let)
}

fun runTries() { //todo run returns the result of it and use 'this' instead of 'it' in let
    val run = arrayOf(1, 2, 3).run {
        System.err.println(get(1))
        size }
    println(run)
}

fun withTries() { //todo reference the object, do some stuff and can return a value
    val list = listOf(1, 2, 3)

    val with = with(list) {
        println(this[0].inc())
        println(get(2))
        7
    }

    println(with)
}

fun lazyTries() {
    val lazy = lazy { 1 + 1 }

    System.err.println(lazy.value) //todo only here function is invoked
}

fun useTries() { //todo without needing the try/catch/finally block.
    val bufferedReader = BufferedReader(null)
    val use = bufferedReader.use { it.lines() } //todo uses resource and closes it

    print(bufferedReader.readLine()) //todo throws an exception cause resource is closed in 'use' fun
}

fun repeatTries() {
    repeat(5) { print("Hey") }
}

@Throws(IOException::class) //todo for java usage in try/catch blocks
fun require_assert_checkTries(string: String) {
    require(string.length > 10) {"Put another string here"} //todo IllegalArgumentException
    assert(string.contains('b')) {"Should contain b"} //todo  java -ea -jar enables assertions
    check(string.endsWith('g')) {"Should end with 'g'"} //todo IllegalStateException
}