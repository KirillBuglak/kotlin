package propertyTesting

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

object MyGenerator: Gen<String> {
    override fun generate(): String {
        return "just Greg"
    }

}

class PropTesting: StringSpec() {

    val list = listOf(1,2,3,4)

    init {
        "lengths check with default generator" {
            forAll { first: String, second: String ->
                (first + second).length == first.length + second.length
            }
        }
        "int generator" {
            forAll(Gen.int()) { i ->
                val divided = i / 2.0
                divided * 2 == i.toDouble()
            }
        }
        "oneOf generator" {
            forAll(Gen.oneOf(list)) {
                val divided = it / 2.0
                divided * 2 == it.toDouble()
            }
        }
        "my Generator" {
            forAll(MyGenerator) {
                it.startsWith("just")
            }
        }

    }
}