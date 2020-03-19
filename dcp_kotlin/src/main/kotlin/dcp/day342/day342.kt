package dcp.day342

// Non-recursive.
fun <T, R> Collection<T>.fold(
    initial: R,
    combinator: (accumulator: R, nextElement: T) -> R): R {

    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combinator(accumulator, element)
    }
    return accumulator
}

// Tail-recursive.
fun <T, R> Collection<T>.reduce(
    initial: R,
    combinator: (accumulator: R, nextElement: T) -> R): R {

    tailrec
    fun aux(accumulator: R, elements: Collection<T>): R = when {
        elements.isEmpty() -> accumulator
        else -> aux(combinator(accumulator, elements.first()), elements.drop(1))
    }

    return aux(initial, this)
}

fun main() {
    val lst = listOf(1, 2, 3, 4, 5)
    println(lst.reduce(0){acc: Int, next: Int -> acc + next})
    println(lst.fold(0){acc: Int, next: Int -> acc + next})
    println(lst.reduce(0, {acc: Int, next: Int -> acc + next}))
    println(lst.fold(0){acc: Int, next: Int -> acc + next})
}