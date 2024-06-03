package basics

import java.io.File
import java.time.LocalTime as LT // TODO: can assign names to imports

class Basics {
    private val s = "Grand"
    private var model = "Park"

    val explicit: Double = 12.2

    val int = 123
    val long: Long = int.toLong() // TODO: must convert Types Explicitly

    /**bitwise**/
    val leftShift = 1 shl 2
    val rightShift = 1 shr 2
    val inv = 1.inv()
    val and = 1 and 0
    val or = 1 or 2
    val xor = 1 xor 0b0101
    /**bitwise**/

    val bool = and > 2 && or < 1
    val char = 'f'
    val str = "String"
    val unicodeChar = '\u1111'
    val array = arrayOf(1,2,3)
    val generatedArray = Array(3) { e -> e * 8 }
    val someVal = array[2]
    val anotherVal = array.get(1)

    val localTime = LT.now()

    val firstString = "first"
    val secondString = firstString + "Second"

    val thirdStrign = "Third + $firstString"
    val fourth = "fourth - ${firstString.length}"
    val fifth = "fifth - $explicit"

    val range = "a".."c"
    val range2 = 1..int
    val isIn = "b" in range
    val countDown = 100.downTo(12)
    val countUp = 12.rangeTo(100)

    val steppedRange = countUp.step(3)
    val steppedReversedRange = steppedRange.step(2).reversed()

    val nullVal = null
    val nullString: String? = null


    fun some() {
        model = "Car" // TODO: VAR can be reassigned

        var name: String
        name = "more kotlin"
    }

    fun increment(s: Int) = s + 1
}

fun main() {
//    instantiation()
//    comparing()
//    printCustomerExample()
//    ifAndTryResults()

}

private fun ifAndTryResults() {
    val k = 1
    val result =
        if (k == 1) {
            23
        } else {
            0
        }
    println(result)
    println()
    val result2 =
        try {
            k / 0
            k //todo the last expression is the result
        } catch (e: Exception) {
            -k
        }
    println(result2)
}

private fun printCustomerExample() {
    val customer = Customer("John")
    customer.printCustome()
}

private fun comparing() {
    val a = File("file")
    val b = File("file")
    val c = a
    println(a === b) //Reference
    println(a === c)
    println(a == b) //Value
    println(null === "greg") //null Safe
    println(null == "greg") //null Safe
}

private fun instantiation() {
    val customer = Customer("Man")
    println(customer)
}