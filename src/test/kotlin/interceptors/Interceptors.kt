package interceptors

import io.kotlintest.ProjectConfig
import io.kotlintest.Spec
import io.kotlintest.TestCaseContext
import io.kotlintest.matchers.endWith
import io.kotlintest.matchers.should
import io.kotlintest.matchers.startWith
import io.kotlintest.specs.StringSpec


val myTestCaseInterceptor: (TestCaseContext, () -> Unit) -> Unit = {
    context, test ->
    val start = System.currentTimeMillis()
    test()
    val finish = System.currentTimeMillis()
    System.err.println("Duration - ${finish - start}ms")
}
val mySpecInterceptor: (Spec, () -> Unit) -> Unit = {
    context, tests ->
    val start = System.currentTimeMillis()
    tests()
    val finish = System.currentTimeMillis()
    System.err.println("Duration - ${finish - start}ms")
}

class TestCaseInterceptor: StringSpec() {
    init {
        "with interceptor" {
            "Greg" should startWith("Gre")
        }.config(interceptors = listOf(myTestCaseInterceptor)) //todo TestCase interceptor
        "without" {
            "Greg" should startWith("Gre")
        }
    }
}

class SpecCaseInterceptor: StringSpec() {
    override val specInterceptors: List<(Spec, () -> Unit) -> Unit>
        get() = listOf(mySpecInterceptor) //todo applies to the whole class

    init {
        val string = "Mark"
        "starts" {
            string should startWith("M")
        }
        "ends" {
            string should endWith("k")
        }
    }
}