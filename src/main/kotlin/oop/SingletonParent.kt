package oop

open class SingletonParent {

    constructor(string: String = "Greg") //todo just for reflexion
    constructor(int: Int) //todo just for reflexion

    private var count = 0;

    fun increment(): Int {
        return ++count
    }
}