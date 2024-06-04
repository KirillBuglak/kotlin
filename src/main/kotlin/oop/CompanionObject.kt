package oop

class CompanionObject private constructor(){ //todo private constructor - since we only use static meth

    init {
        System.err.println("Just init private constructor")
    }
    companion object NamedCompanion: CompObjectInterface, ExtendThis() { //todo we can have only one companion object per class

        const val string = "string"
        override fun doSomeStatic() {
            CompanionObject()
            println("Some static stuff")
        }

        fun doOtherStaticStuff() {
            println("Other static stuff")
        }
    }

    object JustObject {
        const val string = "Hey"
        fun namedFun() {
            println("Named companion")
        }
    }
}

interface CompObjectInterface { //todo companion object can implement interfaces and extend classes
    fun doSomeStatic()
}

open class ExtendThis {
    fun borrowedFun() {
        println("Got this fun")
    }
}

fun main() {
    CompanionObject.doSomeStatic()
    CompanionObject.doOtherStaticStuff()
    println(CompanionObject.string)
    CompanionObject.borrowedFun()
    println()

    CompanionObject.JustObject.namedFun() //todo if it's just an object - we should put it's name
    println(CompanionObject.JustObject.string)
    println()

    funFromFile()
}