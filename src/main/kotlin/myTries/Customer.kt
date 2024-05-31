package myTries

data class Customer(var name: String) {
    fun hasLongName() = name.length > 10
}
