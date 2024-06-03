package basics

import java.io.File
import java.time.LocalDate

fun main() {
//    println(isNumber(23))
//    smartCast(LocalDate.now())
//    smartCheck("aa")
//    casting()
//    anotherCasting()

}

private fun anotherCasting() {
    val any = "/home/users"
    val string: String? = any as String
    val file: File? = any as? File
    println(string)
    println(file)
}

private fun casting() {
    println(getStringLength("hey"))
    println(getStringLengthMayBeNull(null))
}

private fun isNumber(any: Any): Boolean {
    return any is Number
}

private fun smartCast(any: Any) {
    if (any is LocalDate) {
        println(any.year)
    } else {
        println("Hm")
    }
}

private fun smartCheck(any: Any) {
    println(any is String && any.length == 2)
    println(any !is String || any.length == 1) //todo we know that on the right side we have a string
}

private fun getStringLength(any: Any): Int {
    val string = any as String //todo explicit casting
    return string.length
}

private fun getStringLengthMayBeNull(any: Any?): Int {
    val string: String? = any as? String
    return string?.length ?: 0
}