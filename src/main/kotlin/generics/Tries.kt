package generics

import java.io.Serializable

fun main() {
//    simpleGenericTries()
//    multiboundTries()

}

private fun multiboundTries() {
    val multiBound = MultiBound<Int>()
    multiBound.some = 12
    println(multiBound.some)
    println(multiBoundFun("Greg")) //todo multiBound fun
}

fun <T> multiBoundFun(hey: T): T where T: Serializable, T: Comparable<T> {
    return hey
}

private fun simpleGenericTries() {
    val upperGener = UpperGener<String>()
    upperGener.some = "Greg"
    println(upperGener.some)
}

class UpperGener<T: Comparable<T>> { //
    var some: T? = null
}

class MultiBound<T> where T: Comparable<T>, T: Number { //todo using 'where' for multiBounding
    var some: T? = null
}

