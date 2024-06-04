package oop

interface Interface {
    fun takeThis()
}

class Implementor: Interface {
    override fun takeThis() {
        println("Took it")
    }
}

class UseImplementor: Interface by Implementor() //todo here we use a composition with 'by' word - it allows us to write and use different implementors
class ImplementIt: Interface {
    override fun takeThis() {
        println("Implemented myself") //todo this could've been put to implementor, like the example above
    }

}

fun main() {
    UseImplementor().takeThis()
}