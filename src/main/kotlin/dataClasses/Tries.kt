package dataClasses

fun main() {
//    copyTries()
    destructedDeclarationTries()
}

private fun destructedDeclarationTries() {
    val (int, string) = DataClass(1, "a")
    System.err.println("int - $int, string - $string")

    val (i, s) = MyOwnDestruction(1, "G")
    println("int - $i, string - $s")

    val (i1, s1) = SimpleClass(1, "f")
    println("int - $i1, string - $s1")
}

private fun copyTries() {
    val dataClass1 = DataClass(1, "a")
    val dataClass2 = dataClass1.copy(string = "b") //todo can change only some params
    println(dataClass1)
    println(dataClass2)
}

data class DataClass(val int: Int, val string: String)

class MyOwnDestruction(val int: Int, val string: String) {
    operator fun component1() = int //todo have to implement those funs to use Destructed declaration
    operator fun component2() = string
}

class SimpleClass(val int: Int, val string: String)

operator fun SimpleClass.component1() = this.int //todo here we implemented extension funs for simple class to use Destructed declaration
operator fun SimpleClass.component2() = this.string