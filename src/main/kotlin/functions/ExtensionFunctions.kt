package functions

fun main() {

}

fun <E> List<E>.getLast(): E {
    return get(size - 1)
}