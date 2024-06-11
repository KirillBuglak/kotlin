package inspectors

import io.kotlintest.forAll
import io.kotlintest.forAtLeastOne
import io.kotlintest.forOne
import io.kotlintest.forSome
import io.kotlintest.matchers.*
import io.kotlintest.specs.StringSpec


class Inspectors: StringSpec() {
    init {
        val list = mutableListOf("a 1", "b 2", "c 3")

        "forAll" {
            forAll(list) {
                val toInt = it.substring(it.length - 1).toInt()
                toInt shouldNot beLessThan(1)
            }
        }
        "forOne" {
            forOne(list) {//todo only exactly one element should comply
                it should startWith("b")
            }
        }
        "forSome" {
            forSome(list) {
                it should (startWith("b") or endWith("1")) //todo will fail if all elements pass the test
            }
        }
        "forAtLeastOne" { //todo allows to be passed when only one or all elements comply
            forAtLeastOne(list) {
                it should (startWith("b") or endWith("1") or endWith("3"))
            }
        }
    }
}
