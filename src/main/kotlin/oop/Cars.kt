package oop

enum class Cars(private val model: String): GetModel {

    BMW("x5"),
    Merc("clk"),
    Honda("crv");

    override fun getModel(): String {
        return model
    }}

interface GetModel {
    fun getModel(): String
}

fun main() {
    Cars.entries.forEach {
        System.err.println(it)
    }
    val of = Cars.valueOf("Merc")
    System.err.println(of.getModel())
    println()
    Cars.entries.forEach {
        println(it.getModel())
    }
}