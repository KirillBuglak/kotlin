package functions

import java.time.LocalDate

var some = "Const"

fun main() {
//    consumerNoOut { print("Man") }
//    println(argsConsumer(1, 2.5) { "Hey $it" })
//    returnFunTries()
//    closureTries()
//    anonymousFunTries()
//    funRefTries()
//    println("Harry".literalReceiverFun())
//    litReceiver(2) {this > 2}
//    composeTries()
//    litReceiver(2) {this > 2} //todo using Curring here with {} instead of specifying fun as a parameter
//    curring()
    typeAlias(listOf("a", "b", "c"))

}
typealias StringList = List<String>

fun typeAlias(list: StringList) { //todo using typeAlias StringList
    println(list)
}

fun curring() {
    fun <A, B, R> Function2<A, B, R>.curried(): (A) -> (B) -> R = {
        a -> {
            b -> this(a, b)
        }
    }

    fun <A, B, C, R> Function3<A, B, C, R>.curried(): (A) -> (B) -> (C) ->  R = {
        a -> {
            b -> {
                c -> this(a, b, c)
            }
        }
    }

    fun some(int: Int, string: String): String {
        return "hey $int and $string"
    }

    val result = ::some.curried()(1)("Don") //todo applied curring
    println(result)

}

private fun composeTries() {
    val compFun: (LocalDate) -> Int = composeFun(LocalDate::toString, String::length)
    println(compFun(LocalDate.now()))

    val compFun2: (LocalDate) -> Int = LocalDate::toString compose String::length //todo infix usage
    println(compFun2(LocalDate.now()))

    val compFun3: (LocalDate) -> Int = LocalDate::toString + String::length //todo operator usage
    println(compFun3(LocalDate.now()))
}

operator fun <A, B, C> Function1<A, B>.plus(fn: (B) -> C): (A) -> C = { fn(this(it)) } // TODO: the same function as the one below but here we use operator
infix fun <A, B, C> Function1<A, B>.compose(fn: (B) -> C): (A) -> C = { fn(this(it)) } // TODO: the same function as the one below but more concise and clearer

inline fun <A, B, C> composeFun(crossinline fn1: (A) -> B, noinline fn2: (B) -> C): (A) -> C = {//todo here we use 'inline'/'crossinline' to stop producing separate classes and put this function in the caller's class | 'noinline' is the opposite
    val b = fn1(it)
    val c = fn2(b)
    c
}

fun String.literalReceiverFun() = length < 2

fun litReceiver(int: Int, fn: Int.() -> Boolean) { //todo literal Int. - literal Receiver
    println(int.fn())
}

private fun funRefTries() {
    val list = listOf("a", "b", "cb")
    println(list.filter { checkString(it) }.size)
    println(list.filter(::checkString).size) //todo top level fun reference
    println(list.filter(String::contA).size) //todo extension fun reference
    println(list.filter(Some::checkSomeString).size) //todo comp object member fun reference
    println(list.filter(Some()::checkSomeString).size) //todo just member fun reference

    println(list.filter("b"::equals).size) //todo Expression reference | Bound reference
}

fun String.contA(): Boolean = this.contains("a")

class Some {
    fun checkSomeString(string: String) = string.contains("a")
    companion object {
        fun checkSomeString(string: String) = string.contains("a")
    }
}


fun checkString(string: String) = string.contains("b")

private fun anonymousFunTries() {
    println(listOf(1, 2, 3).filter(fun(it) = it >= 3).size) //todo we use fun() = ... as a anonymous fun
}

private fun closureTries() {
    val clFun = closureFun()
    clFun()
}

fun closureFun(): () -> Unit = {
    println("here is a constant - $some") // TODO: Here we have access to outer members
    some = "Another" //todo we can even mutate outer members
    println("here is a constant - $some")
}

private fun returnFunTries() {
    val getLength = returnFun()
    println(getLength("adc"))
    val repeatString = returnFunWithArgs(3)
    repeatString("Greg")
    val assignedFun: (String) -> Unit = { print(it) }
    val assignedFunOther = { it: String -> println(it) } //todo the same as fun above but more concise and clear
    assignedFun("Hey")
    assignedFunOther("You")
    val twoArgsFun = {a: String, b: Int -> println("here is $a and $b") }
    twoArgsFun("Qwerty", 2)
}


fun consumerNoOut(fn: () -> Unit) {
    repeat(5) { fn() }
}

fun argsConsumer(int: Int, double: Double, fn: (Number) -> String): String {
    return when {
        int > double -> fn(int)
        else -> fn(double)
    }
}

fun returnFun(): (String) -> Int = { it.length }

fun returnFunWithArgs(times: Int): (String) -> Unit = {
        val str = it
        repeat(times) { println(str)
    }
}

