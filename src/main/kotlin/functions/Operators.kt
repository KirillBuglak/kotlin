package functions

fun main() {
//    overridePlusOperator()
//    in_containsTries()
    setGetArray()
}

private fun plusAssi() {
}

private fun setGetArray() {
    val arrayOf = arrayOf(1, 2, 3)
    var i = arrayOf[0] //todo [] getter
    arrayOf[0] = 2 //todo = setter
    arrayOf.forEach { print(it) }
}

private fun in_containsTries() {
    val list = listOf(1, 2, 3)
    println(1 in list)
    println(list.contains(1)) //todo same as the above example
}

private fun overridePlusOperator() {
    operator fun <E> List<E>.plus(other: Any?): List<E> { //todo overridden operator fun
        System.err.println(other)
        return this.plus(element = other) as List<E>
    }
    print("a" + "B")
    val ints = listOf(1, 2, 3) + 4
    print(ints)
}


