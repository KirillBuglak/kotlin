package oop

data class MyDataClass(val string: String, val int: Int) {
}

fun main() {
    val myDataClass = MyDataClass("abc", 12)
    System.err.println(myDataClass.string)
    System.err.println(myDataClass.int)
    System.err.println(myDataClass)
}