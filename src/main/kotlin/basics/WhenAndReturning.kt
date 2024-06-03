package basics

fun main() {
//    stringFun("c")
//    println(returnNumber("cc"))
//    println(isAbs(3))
//    println(withRange(3))
//    withAnyTries()
//    println(withAnyNoValue(55.5))
//    printLessThanTwo()
    printLessTries()

}

private fun printLessTries() {
    printLessThanTwoWihtLabel()
    printLessThanTwoOrdinaryFor()
}

private fun withAnyTries() {
    println(withAny(null))
    println(withAny(5))
    println(withAny(5.2))
    println(withAny("Greg"))
}

private fun stringFun(string: String) {
    when (string) {
        "a" -> println("Hey - $string")
        "b" -> println("You - $string")
        else -> println("something else - $string")
    }
}

private fun returnNumber(string: String): Int {
    return when (string) {
        "a", "c" -> 1
        "b" -> 2
        string.substring(1,2) -> 44
        else -> 3
    }
}

private fun isAbs(x: Int): Boolean {
    return when (x) {
        Math.abs(x) -> true //todo can use function to verify the value
        else -> false
    }
}

private fun withRange(int: Int): Int{
    return when (int) {
        in setOf(7,3) -> 33
        in 1..3 -> 3
        in -5..0 -> 5
        else -> 66
    }
}

private fun withAny(any: Any?): Int{
    return when (any) { //todo using when with value for easier reference to value
        is String -> any.length //todo SmartCast
        is Int -> when (any) { //todo SmartCast and another 'when'
            in 1..3 -> 3 //todo Ranges
            else -> 55
        }
        else -> {
            val d: Double? = any as? Double //todo nullable case
            d?.div(2)?.toInt() ?: 77
        }
    }
}

private fun withAnyNoValue(any: Any?): Int {
    return when {
        any is String -> any.length
        any is Int -> when {
            any in 1..3 -> 3
            else -> 55
        }
        any is Double && any == 55.5 -> 77
        else -> 11
    }
}

private fun printLessThanTwo() {
    val list = listOf(1, 2, 3, 4)

    list.forEach(fun(x) { //todo fun(x) declares the function and return - returns the result of this function only
        if (x < 2) println(x)
        else return@forEach
    })
    println("This line will still execute")
}
private fun printLessThanTwoWihtLabel() {
    val list = listOf(1, 2, 3, 4)

    list.forEach greg@ {
        if (it == 1) return@greg //todo like 'continue' in java
        if (it <= 2) println(it)
        else return@greg //todo returns only to label and continues the meth | like 'break' in java
//        else return //todo returns the whole meth
    }
    println("This line will still execute")
}
private fun printLessThanTwoOrdinaryFor() {
    val list = listOf(1, 2, 3, 4)

    list.forEach {
        if (it < 2) println(it)
        else return
//        else return@forEach //todo return only this function
    }
    println("This line will not execute")
}