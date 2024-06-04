package oop

sealed class Sealed {
    open val str = "sealed"
    class One: Sealed() { //todo we have to implement derived classes inside a sealed one
        override val str = "one"
    }
    class Two: Sealed() {
        override val str = "Two"
    }
}

fun main() {
    doStuff(Sealed.Two())
}

fun doStuff(sealed: Sealed) {
    when (sealed) {
        is Sealed.One -> println(sealed.str)
        is Sealed.Two -> System.err.println(sealed.str) //todo we do not use else statement here cause we only have two classes extending a sealed one
    }
}