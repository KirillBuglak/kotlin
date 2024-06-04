package oop

open class ParentClass {
    private val a = "a"
    protected open val b = "a"
    internal open val c = "c"
    public val d = "d"
}

class Derived: ParentClass() {
    public override val b = "overridden b" //todo overriding value's visibility modifier
    public override val c = "overridden c"
}

fun main() {
    val derived = Derived()
    println(derived.b)
    println()
    println("Parent's fields")
    derived.javaClass.superclass.declaredFields.forEach {//todo some sort of reflexion here
        it.trySetAccessible()
        println("Field ${it.name} = ${it.get(derived)}")
    }
}