package collections

fun main() {
//    listIterTries()
//    asSeq()
//    emptyArray()
//    primitiveDoubleArray()
//    arrayForLoop()
//    flattenedList()
//    plusTries()
//    orElseTries()
//    zip()
//    foldRight()
//    convertToArrays()
//    view()
//    indexedAccess()
//    seqWithConstraint()

}

private fun seqWithConstraint() {
    val sequence = sequenceOf(1, 2, 3).constrainOnce()
    sequence.forEach { println(it) }
    sequence.forEach { println(it) }
}

private fun indexedAccess() {
    val listOf = listOf(1, 2, 3)
    System.err.println(listOf[2])
    val mapOf = mapOf(1 to 'a', 2 to 'b')
    System.err.println(mapOf[2])
}

private fun view() {
    val mutableListOf = mutableListOf(1, 2, 3)
    val view: List<Int> = mutableListOf //todo creating view by using immutable interface for created mutable collection
    println(view)
}

private fun convertToArrays() {
    listOf(1, 2, 3, 4).toTypedArray()
    listOf(1, 2, 3, 4).toIntArray()
}

private fun foldRight() {
    val foldRight = listOf(1, 2, 3, 4).foldRight("") { num, st ->
        st + num
    }
    println(foldRight)
}

private fun zip() {
    val list = listOf(1, 2, 3, 4)
    val zip: List<Pair<Int, Char>> = list.zip(listOf('a', 'b', 'c')) //todo would left 4 out
    zip.forEach(::println)
}

private fun orElseTries() {
    val list = listOf(1, 2, 3)
    System.err.println(list.elementAtOrElse(55) { 100 })
}

private fun plusTries() {
    val mutableSet = mutableSetOf(1, 2, 3)
    println(mutableSet)
    mutableSet.plus(333) //todo plus creates a new collection
    println(mutableSet)
    mutableSet.plusAssign(444) //todo plusAssign adds new element to the same collection
    println(mutableSet)
}

private fun flattenedList() {
    println(mutableListOf(listOf(1, 2), listOf(3, 4)).flatten())
}

private fun arrayForLoop() {
    val arrayOf = arrayOf(1, 2, 3, 4)
    val filterIndexed = arrayOf.filterIndexed { ind, el -> ind % 2 == 0 }.toIntArray()
    for (i in filterIndexed.indices) {
        println(filterIndexed[i])
    }
}

private fun primitiveDoubleArray() {
    val doubleArrayOf = doubleArrayOf(2.2, 3.3, 4.4)
    println(doubleArrayOf.joinToString(", "))
}

fun listIterTries() {
    val listIterator = mutableListOf(1, 2, 3).listIterator()
    println(listIterator.next())
    println(listIterator.next())
    println(listIterator.previous())
    listIterator.next()
    listIterator.set(5)
    println(listIterator.previous())
}

fun asSeq() {
    val list = mutableListOf(1, 2, 3)
    val asSequence = list.asSequence()
    println(asSequence.average())
}

private fun emptyArray() {
    val emptyArray = emptyArray<String>()
    println(emptyArray.joinToString(","))
}