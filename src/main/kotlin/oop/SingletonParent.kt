package oop

open class SingletonParent {
    private var count = 0;

    fun increment(): Int {
        return ++count
    }
}