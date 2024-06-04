package oop

interface MyInterface {
    val name: String
        get() = "NoName"

    val age: Int

    fun getSome(): String {
        return "NoWay"
    }

    fun getAnother(): String {
        return "another"
    }

    fun getMessage(): String {
        return "Your name is $name, you are $age years old"
    }

    fun implementMe(double: Double): String
}

class Impl(override val age: Int) : MyInterface {

    var changing: String? = null
        get() { //todo creating getters and setters
            return if (field == null) {
                "Mark"
            } else {
                field
            }
        }
        set(value) {
            field = "Greg $value"
        }
    override val name: String
        get() = super.name

    override fun implementMe(double: Double): String {
        return "Number is $double"
    }
}

fun main() {
    val impl = Impl(12)
    println(impl.implementMe(12.2))
    println(impl.getMessage())
    println()

    println(impl.changing)
    impl.changing = "John"
    println(impl.changing)
}