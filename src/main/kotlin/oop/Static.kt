package oop

//todo This declaration equals to public final class in java
fun main() {
    println(someStatic("ab"))
}

private fun someStatic(string: String): Int {
    require(string.length < 3) { "Can't take string with length > 3" }
    return 123
}