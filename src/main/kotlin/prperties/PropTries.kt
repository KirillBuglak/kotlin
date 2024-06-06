package prperties

import functions.string
import kotlin.reflect.KProperty

fun main() {
//    lateInitTries()
//    println(AnotherImpl("Main").string)
//    propsTries()
//    propsByMapTries()
    simplerPropsByMap()

}

private fun simplerPropsByMap() {
    val simpler = PropsByMapSimpler(mapOf("int" to 3, "string" to "Some"))
    println(simpler.int)
}

private fun propsByMapTries() {
    val propsByMap = PropsByMap()
    println(propsByMap.int)
    println(propsByMap.string)
    propsByMap.string = "Another"
    println(propsByMap.string)
}

private fun propsTries() {
    println(AnotherImpl("None").anotherString)
}

private fun lateInitTries() {
    val withLateProp = WithLateProp()
    withLateProp.initProperty("Greg") //todo if we comment this line we get Exception - lateinit property string has not been initialized
    println(withLateProp.string)
}

class WithLateProp {
    lateinit var string: String

    fun initProperty(string: String) {
        this.string = string
    }
}

interface Inter {
    val string: String
}

class ImplInter(override val string: String): Inter {
    var hey: String = "Man"
    operator fun getValue(ref: Any?, property: KProperty<*>): String {
        System.err.println(property.isConst)
        return hey;
    }
    operator fun setValue(ref: Any?, property: KProperty<*>, value: String)
    {
        hey = value
    }
}

class AnotherImpl(string: String): Inter by ImplInter(string) { //todo here we delegated properties
    val anotherString: String by ImplInter("Greg") //todo delegating particular property
}

class MapDelegate() {
    private val map = mutableMapOf<String, Any?>()
    operator fun <T> getValue(ref: Any?, property: KProperty<*>): T {
        return map[property.name] as T;
    }
    operator fun <T> setValue(ref: Any?, property: KProperty<*>, value: T)
    {
        map[property.name] = value
    }
}

class PropsByMap {
    private val mapDelegate = MapDelegate() //todo creates no overhead when using Map, much better than 'ImplInter'

    var int: Int by mapDelegate
    var string: String by mapDelegate

    init {
        mapDelegate.setValue(this, ::int, 3)
        mapDelegate.setValue(this, ::string, "John")
    }
}

class PropsByMapSimpler(map: Map<String, Any?>) { //todo using much simpler approach than one above - result is the same
//class PropsByMapSimpler(val map: MutableMap<String, Any?>) { //todo val is used for read and Write
    val string by map
    val int by map
}