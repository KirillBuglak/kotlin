package oop

import java.time.LocalDate

open class SomeClass(val s: String) {

    protected val greg = "Greg"

    protected constructor(string: String, int: Int?) : this(string) { //todo can only be accessed by subclasses
        println(int)
    }

    internal constructor() : this("a4", null) {
        System.err.println("No args constructor")
    }
    init { //todo relates to primary constructor
        require(s.length >= 2) {"Length can't be less than 2"}
        println("Initializing - ${s.length}")
    }

    override fun toString(): String {
        return "SomeClass(s='$s')"
    }


}

class ExtendedClass internal constructor(string: String, int: Int?) : SomeClass(string, int) { //todo we need to use constructor word here due to visibility modifier added
    private val name = string
    private val age = int

    fun getName() = name
    fun getAge() = age
    fun getGregName() = greg
}

fun main() {
    val someClass = SomeClass()
    val extendedClass = ExtendedClass("333", 3)
    println(extendedClass.getAge())
    println(extendedClass.getGregName())
}