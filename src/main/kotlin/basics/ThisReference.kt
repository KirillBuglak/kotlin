package basics

class ThisReference(val ref: String) {
    inner class Inside {
        fun printOuterRef() {
            println(this@ThisReference.ref)
        }
    }
}