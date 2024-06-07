package basics

data class Customer(var name: String) {

    fun hasLongName() = name.length > 10
    fun printCustomer() {
        println(this)
    }

    fun someFun() {
        println("Hey there")
    }
}
