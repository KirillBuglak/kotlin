package matchers

import io.kotlintest.matchers.*
import io.kotlintest.specs.FeatureSpec
import io.kotlintest.specs.StringSpec

class Strings : FeatureSpec() {
    init {
        feature("str matchers") {
            val string = "String"
            scenario("starts") {
                string should startWith("S")
            }
            scenario("include") {
                string should include("ring")
            }
            scenario("ends") {
                string should endWith("g")
            }
            scenario("length") {
                string should haveLength(6)
            }
            scenario("match") {
                string should match("Str...")
            }
        }
    }
}

class CollectionMatchers : FeatureSpec() {
    init {
        feature("collection matchers") {
            val list = mutableListOf(1, 2, 3, 5, 4)
            val map = mutableMapOf(1 to 1, 2 to 2, 3 to 3)
            scenario("contain") {
                list should contain(3)
            }
            scenario("size") {
                list should haveSize(5)
            }
            scenario("sorted") {
                list.sort()
                list shouldBe sorted<Int>()
            }
            scenario("singleElement") {
                val listOf = listOf(list[3])
                listOf shouldBe singleElement(5)
            }
            scenario("containsAll") {
                list should containsAll(5, 3, 1)
            }
            scenario("empty") {
                emptyList<Int>() should beEmpty()
            }
            scenario("haveKey") {
                map should haveKey(2)
            }
            scenario("haveValue") {
                map should haveValue(2)
            }
            scenario("containMap") {
                map should contain(2, 2)
            }
        }
    }
}

class FloatingPointMatchers : StringSpec() {
    init {
        val num = 1.23456
        "exact" {
            num shouldBe 1.23456
        }
        "plusMinus" {
            num shouldBe (1.2 plusOrMinus 0.3)
        }
    }
}

class Throw : StringSpec() {
    init {
        val throws = { 2 / 0 }
        "should Throw" {
            shouldThrow<ArithmeticException> {
                throws()
            }
        }
    }
}

class Combining : StringSpec() {
    init {
        val string = "String"
        "string combine" {
            string should (haveLength(2) or (startWith("Str") and include("g")))
        }
    }
}

fun good() = object: Matcher<String> {
    override fun test(value: String): Result {
        return if (value.startsWith("S") and value.contains("ri")) {
            Result(true, "Yep")
        } else {
            Result(false, "Nope")
        }
    }
}

class TestCustomMatcher: StringSpec() {
    init {
        val string = "String"
        "should be good" {
            string shouldBe good()
        }
    }
}