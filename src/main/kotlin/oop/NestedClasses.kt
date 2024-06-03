package oop

class NestedClasses {
    val a = "a"
    val string = "abc"
    val innerOne = InnerOne() //todo can use inside only
    class InnerOne {
//        val str = string //todo can't call outer class's members
    }

    inner class InnerTwo {
        val a = "b"
        val str = string //todo here we CAN call outer class's members - due to 'inner' word

        fun getInnerA() = this@InnerTwo.a
        fun getOuterA() = this@NestedClasses.a
    }
}

fun main() {
//    NestedClasses().InnerOne() //todo can't use InnerOne - no 'inner' word found
    val innerTwo = NestedClasses().InnerTwo() //todo can use InnerTwo - due to 'inner' word
    println(innerTwo.str)
    println(innerTwo.getInnerA())
    println(innerTwo.getOuterA())
}