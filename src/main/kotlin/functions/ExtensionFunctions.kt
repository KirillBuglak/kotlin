package functions

fun main() {
//    val list = listOf(1, 2, 3)
//    println(list.getLast())
//    print(list.getOtherTypeElement(0, String))
//    "a".printThisThing()
//    val s: String? = null
//    s.printThisThing()
//    println(ExtFunInClass().extensionReceiverFun("Greg"))
//    println(ExtFunInClass.getMiddle("abcdef"))
//    pairTries()

}

private fun pairTries() {
    val pair = getPair(1, 3.3)
    println("pair - ${pair.first} and ${pair.second}")
    val (int, double) = getPair(1, 3.3) //todo destructive declaration, cool thing
    println("destructive pair - $int and $double")
}

fun getPair(int: Int, double: Double): Pair<Int, Double> {
    return Pair(int * 2, double / 3)
}

fun <E> List<E>.getLast(): E {
    return get(size - 1)
}

fun <E, R> List<E>.getOtherTypeElement(int: Int, type: R): R {
    val element = this[int] //todo reference to receiver instance - the object that the function was invoked on
    return element as R
}

fun Any?.printThisThing() {
    println(this ?: "Nope")
}

class ExtFunInClass {
    private val string = "String"
    private fun String.addThisDispatch(string: String): String { //todo extension fun inside class can't be used outside even without private modifier
        return this.plus(string).plus(this@ExtFunInClass.hashCode()) //todo this fun is a 'dispatch' receiver | here we use a qualified 'this', otherwise 'this' would refer to a string
    }
    fun extensionReceiverFun(string: String): String = "A".addThisDispatch(string) //todo this fun is 'extension' receiver

    companion object Hey {
        fun String.getTheMiddle(): String {
            return substring(1..3)
        }

        fun getMiddle(string: String): String = string.getTheMiddle()
    }
}