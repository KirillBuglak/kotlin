package nullSafetyAndReflexion

import basics.Customer
import oop.SingletonParent
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberExtensionFunctions
import kotlin.reflect.full.superclasses

fun main() {
//    nullSafeOperators()
//    forceOperator()
//    elvisTries()
//    safeCasting()
//    optionalTries()
//    reflexionTries()
//    funReflexion()

}

private fun funReflexion() {
    val customerClass = Customer::class
    val function = customerClass.functions.find { it.name == "someFun" }
    function?.call(Customer("Grinch"))
    println(customerClass.memberExtensionFunctions)
    println(customerClass.functions)
}

private fun reflexionTries() {
    val string = "Greg"
    val stringKClass = string::class
    println(stringKClass.supertypes)
    println(stringKClass.superclasses)
    println(stringKClass.visibility)

    val forName: KClass<SingletonParent> = Class.forName("oop.SingletonParent").kotlin as KClass<SingletonParent>
    println(forName.supertypes)
    val createInstance = forName.createInstance()
    println(createInstance.increment())

    println(forName.constructors)
    var par: KParameter? = null
    val constructor = forName.constructors.find {
        val param = it.parameters.get(0)
        par = param
        param.name == "int"
    }

    val inc = constructor?.call(12)?.increment()
    val inc2 = constructor?.callBy(mapOf(par!! to 12))?.increment() //todo the same as above, but calling 'callBY' meth
    println(inc)
    println(inc2)


}

fun tryAnnotation() {
    @MyAnnotation(1) val string = "Man" //todo applied some annotation
}

private fun optionalTries() {
    val optional: Optional<String> = Optional.of("Greg")
    val emptyOp: Optional<String> = Optional.empty()
    println(optional.orElseThrow())
    println(emptyOp.orElse("Marry"))
}

private fun safeCasting() {
    var string = "Greg"
    var int: Int? = string as? Int
    println(int)
}

private fun elvisTries() {
    println(elvis(null))
    println(elvis("A"))
}

fun elvis(string: String?): String {
    return string ?: "There was a null"
}

private fun forceOperator() {
    var string: String? = "Greg"
    var noNullString: String = string!!
    println(noNullString)
}

private fun nullSafeOperators() {
    var string: String? = null
    println(string?.length)
}

@Target(AnnotationTarget.LOCAL_VARIABLE)
annotation class MyAnnotation(val int: Int) {

}