package generics

fun main() {
//    invarianceTries()
//    covarianceAndContravarianceTries()
//    coAndConterVariantFunParamsTries()
//    reifiedTries()
//    recursiveTypeBoundingTries()

}

private fun recursiveTypeBoundingTries() {
    println(Extender(2).compareTo(Extender(3)))
}

private fun reifiedTries() {
    reifiable<String>("Greg")
    reifiable<String>(12)
}

private fun coAndConterVariantFunParamsTries() {
    transformNotCoToCovariant(NotCovariant(mutableListOf(Moto())))
    transformNotCoToContervariant(NotCovariant(mutableListOf(Transport())))
}

private fun covarianceAndContravarianceTries() {
    val moto = ArrayList<Moto>()
    val auto = ArrayList<Auto>()
    val transport = ArrayList<Transport>()
    val covariant: Covariant<Transport> = Covariant(auto)
//    val notCovariant: NotCovariant<Transport> = NotCovariant(moto) //todo here we cannot variate the type cause we do not have 'out' word in class definition
    val contravariant: Contravariant<Auto> = Contravariant(transport)
}

private fun invarianceTries() {
    val list = ArrayList<Transport>()
    list.add(Moto())
    list.add(Auto())
}

open class Transport

class Moto: Transport()

class Auto: Transport()

class Covariant<out T> (val list: MutableList<@UnsafeVariance T>) //todo 'out' allows us to do Covariant<Transport> = Covariant<Auto>
class NotCovariant<T> (val list: MutableList<T>) //todo - default behaviour
class Contravariant<in T> (val list: MutableList<@UnsafeVariance T>) //todo 'in' allows us to do Covariant<Auto> = Covariant<Transport>

fun transformNotCoToCovariant(some: NotCovariant<out Transport>) { //todo here we allow notCovariant type to be covariant in fun - 'out'
    println(some.list)
}
fun transformNotCoToContervariant(some: NotCovariant<in Auto>) { //todo here we allow notCovariant type to be contervariant in fun - 'in'
    println(some.list)
}

fun some(list: List<String>) {

}

@JvmName("another") //todo used for java - cause in java it's considered same as fun above
fun some(list: List<Int>) {

}

inline fun <reified T> reifiable(u: Any) { //todo reified - allows us to use the type inside the function | must use 'inline' word
    if (u is T) {
        println("Types are the same")
    } else {
        println("Types are different")
    }
}

interface CommonLogic<E : CommonLogic<E>>: Comparable<E> { //todo recursive type bounding
    val number: Int //todo <E : CommonLogic<E>> - gives us an opportunity to put common logic in Interface?abstractClass and compare only compatible types
    override fun compareTo(other: E): Int {
        return number.compareTo(other.number) //todo if we used CommonLogic<E> - we wouldn't be able to get other.name
    }
}

class Extender(override val number: Int): CommonLogic<Extender>