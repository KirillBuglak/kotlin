package tableAndConfigTesting

import io.kotlintest.*
import io.kotlintest.matchers.haveLength
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.startWith
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table
import io.kotlintest.specs.StringSpec

val table = table(
    headers("a", "b", "c"), //todo labels row elements when error occurs
    row(1,2,3),
    row(4,5,6),
    row(7,8,0)
)

class TableTest: StringSpec() {
    init {
        "table hard" {
            forAll(table) { a1, b1, c1 ->
                a1 % 3 shouldBe 1
                b1 % 3 shouldBe 2
            }
        }
    }
}

class EventTest: StringSpec() {
    init {
        "1 sec" {
            eventually(1.seconds) {
                "some" should haveLength(4)
            }
        }
        "1 microSec" {
            eventually(1.microseconds) { //todo fails after 1 microsecond | may use timeout in config, like in below example
                "some" should haveLength(4)
                "some" should haveLength(4)
            }
        }
    }
}

object MyTag: Tag()
class ConfigTest: StringSpec() {
    override val oneInstancePerTest = false //todo (default is true) if false - do not resets members before init
    var string = "Continued" //todo if oneInstancePerTest = false 'string' is changed in 'conditioned' to 'so' and it will be used in 'tagged' | if oneInstancePerTest = true -> 'Continued' will be used in 'tagged'
//    val resource = autoClose(javaClass.getResourceAsStream("HelloWorld.jar")) //todo autoClose - closes the resource regardless the result of tests
    init {
        "multiple times" {
            Thread.sleep(1_000)
            "Greg" should startWith("G")
        }.config(invocations = 5,
            threads = 2, //todo change the number of threads
            timeout = 3.seconds
//            , enabled = false
        )

        val enabled = "some".length == 4

        "conditioned" {
            string = "So"
            string should startWith("So")
        }.config(enabled = enabled)

        "tagged" {
            string should startWith("Co")
        }.config(tags = setOf(MyTag)) //todo include -DincludeTags=MyTag into vm options
    }
}