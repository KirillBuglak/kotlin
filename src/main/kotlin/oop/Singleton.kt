package oop

object Singleton: SingletonParent() {

}

fun main() {
    println(Singleton.increment())
}