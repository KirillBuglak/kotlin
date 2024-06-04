package oop

open class Parent(string: String) { //todo we have to add 'open' word to make parent class inheritable

    val prop = string

    init {
        println("Inited")
    }
}

data class DerivedClass(val int: Int, val string: String): Parent(string) {
}

class AnotherDerived: Parent {
    val some: Double
    constructor(string: String, double: Double): super(string) { //todo referring to parent constructor
        this.some = double
    }
}

open class First{
    open fun some(): String {
        return "string"
    }
}

abstract class SecondAbstract: First() {
    abstract override fun some(): String //todo overriding to abstract fun
}

open class Third: SecondAbstract() {

    open val string = "String"
    override fun some(): String {
        return "Another"
    }

}

class Fourth: Third() {

    private var first = First() //todo Composition principal

    override var string: String = "Fourth" //todo here we redefined val to var, otherwise is not possible
    final override fun some(): String { //todo final doesn't allow overriding
        println(super.some())
        return "Greg"
    }

    fun some(string: String): String { //todo just an overloading
        return string
    }
}

interface Rollable {
    fun roll() {
        println("Rolling")
    }
}

open class Roller {
    open fun roll() {
        println("here is the Roller")
    }
}

class RollerBlade: Rollable, Roller() {
    override fun roll() {
        super<Roller>.roll() //todo overriding both meth from class and interface
        super<Rollable>.roll()
        println("And here is a Rollerblade")
    }
}

fun main() {
    println(DerivedClass(1, "G").prop)
    println(AnotherDerived("d", 22.2).some)
    println()

    val third = Third()
    println(third.some())
    val fourth = Fourth()
    println(fourth.some())
    println(fourth.some("Hey man"))
    println()

    println(third.string)
    println(fourth.string)
    fourth.string = "Man"
    println(fourth.string)
    println()

    RollerBlade().roll()
}