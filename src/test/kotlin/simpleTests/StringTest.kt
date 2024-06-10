package simpleTests

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.*

class StringTests : WordSpec() {
    init {
        "Gregor" should {
            "be more than 4 letters" {
                "Greg".length shouldBe 4
            }
        }
    }
}

class ShSpec : ShouldSpec() {
    init {
        "String" {
            should("be better") {
                "Gregr".length shouldBe 5
            }
        }
    }
}

class Beh: BehaviorSpec() {
    init {
        Given("some thing") {
            var string = "Greg"
            var string2 = "Greg"
            When("something is added") {
                string += "ory"
                Then("should be good") {
                    string.length shouldBe 7
                }
            }
            When("subtract") {
                string2 = string2.substring(2)
                Then("should also be good") {
                    string2.length shouldBe 2
                }
            }
        }
    }
}

class FSpec: FeatureSpec() {
    init {
        feature("string") {
            var string = "Greg"
            scenario("when added") {
                string += "A"
                string.length shouldBe 5
            }
            scenario("when subString") {
                string = string.substring(1)
                string.length shouldBe 3
            }
        }
    }
}

class StrSpec: StringSpec() { //todo use this spec as a default
    init {
        "String should be more than 3" {
            "greg".length shouldBe 4
        }
    }
}