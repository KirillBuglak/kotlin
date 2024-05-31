package myTries

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



    fun some() {
        model = "Car" // TODO: VAR can be reassigned

        var name: String
        name = "more kotlin"
    }

    fun increment(s: Int) = s + 1

}