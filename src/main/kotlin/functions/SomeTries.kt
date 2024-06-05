package functions

const val string = "string"
val another = string.take(5) //todo member function

fun someTopLevelFun(string: String) {
    require(string.length >=3) {"Can't use that string"}
    println("Top level Fun")
}
fun main() {
//    println(singleExpressionFun("a"))
//    println(another)
//    outer()
//    listWithLocalFuns(string = "Greg") //todo Named parameter
//    funsWithDefault()
//    println(tailRecurse(5))
//    spreadOperator()
//    myGenericFun(2,3)
//    realGenerics(2)

}

fun <E> myGenericFun(e: E, int: Int) { //todo here we can change E to 'Any?'
    repeat(int) { println(e) }
}

@JvmName("realGen")
fun <E> realGenerics(e: E): E { //todo here we ensure that param and result are of the same type
    println(e)
    return e
}

private fun spreadOperator() {
    val array = arrayOf("1", "2", "3")
    println(varargFun(*array)) //todo using spread operator - '*'
}

fun varargFun(vararg strings: String): String {
    var str = ""
    strings.forEach { str += it }
    return str
}

tailrec fun tailRecurse(int: Int): Int { //todo tailrec word used
    return when (int) {
        3 -> int * 2
        else -> {
            System.err.println("Hey")
            tailRecurse(int - 1) //todo put recursion to the end - tail
        }
    }
}

private fun funsWithDefault() {
    funWithDefault()
    funWithDefault("B")
    funWithDefault(second = "C") //todo can use defaults with Named params/ useful when we omit one param but want to set the next param
}

fun singleExpressionFun(string: String) = string + 2 //todo do not need to declare the return Type

fun outer() {
    localFun()
    fun inner(): String {
        return "inner"
    }
    println(inner())
}

private fun localFun() { //todo AVOID putting params in params list that can be accessed just inside it's body
    println("Another $string") //todo private has access to outer values
}

fun listWithLocalFuns(string: String) {

    fun isTwo(i: Int) = i == 2
    
    for (i in listOf(1, 2, 3)) {
        when { //todo equivalent to if/else below
            isTwo(i) -> println("Yep")
            else -> println(string)
        }
//        if (isTwo(i)) println("Yep")
//        else println("Nope")
    }
}

fun funWithDefault(string: String = "a", first: String = "first", second: String = "second") {
    println(string)
    println(first)
    println(second)
}