package basics

val someVal = "Some"

open class VisibilityModifiers {
    private val privVal = "";
    public val pubVal = "pub"
    protected val protectVal = "protect"
    val unprotectVal = "unprotect"
    inner class InnerClass {
        private val value = protectVal
    }
}

private class PrivateClass {
    fun hey(): String = "String"
    val value = VisibilityModifiers().unprotectVal
    val someValue = someVal
}

internal class InternalClass { //todo opens visibility to current 'module'

}