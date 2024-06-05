package functions

fun main() {
//    infixPair()
//    myAppend()

}

private fun infixPair() {
    val (s, i) = getInfixPair("a", 2)
    println("hey $s and $i")
}

private fun myAppend() {
    val s1 = "a" append "c"
    println(s1)
}

fun getInfixPair(string: String, int: Int): Pair<String, Int> {
    return string to int //todo infix fun to create a 'pair'
}

infix fun String.append(string: String): String {
    return this.plus(string)
}