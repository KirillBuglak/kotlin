package basics

fun main() {
//    whileLoop()
    forLoop()
}

private fun whileLoop() {
    var count = 0
    while (count < 10) {
        println("This will print out $count")
        count++
    }
}

private fun forLoop() {
    val ints = arrayOf(1, 2, 3)
    ints.forEach { e -> System.err.println(e) }
    for (e in ints) {
        println(e)
    }
    println()
    for (e in 2..5) {
        println(e)
    }
    println()
    val chars = arrayOf('a', 'b', 'c')
    for (c in chars.indices) {
        println("Element ${chars[c]} has index $c")
    }
    println()
    var abcd = "abcd"
    for (c in abcd) {
        System.err.println("char '$c' in 'abcd' String")
    }
}