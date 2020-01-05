package dcp.day270
// day270.kt
// Sebastian Raaphorst, 20202.

import kotlin.math.min

// Use a technique called the Fibonacci search.
// https://en.wikipedia.org/wiki/Fibonacci_search_technique

fun List<Int>.fibContains(x: Int): Boolean {
    if (x > last() || x < first())
        return false
    val n: Int = this.size

    // We are trying to find t.
    // 1. q is the smallest fib # greater than or equal to the size of the array, i.e. fib(q) >= list.length &&
    //      if fib(p) >= list.length, then fib(p) >= fib(q).
    // Let p, q be consecutive numbers.
    //      * If x == array[p], we have found the element.
    //      * If x < appay[p], move p and q down two indices each.
    //      * If x > array[p], move p and q down one each index each, but add an offset of p to the net search term?
    fun fibonacci() =
        generateSequence(Pair(0, 1), { Pair(it.second, it.first + it.second) }).map{ it.first }

    // Get the fibonacci sequence with its indices.
    val fibs = fibonacci().withIndex().takeWhile {it.value <= n }.toList()

    // We use a tailrec look to traverse the array by fib numbers so we can achieve O(log n) performance.
    tailrec
    fun aux(p: Int, q: Int, offset: Int = 0): Boolean {
       if (q <= 0) return false

        val index = min(offset + fibs[p].value, n - 1)
        return when {
            x == this[index] -> true
            x < this[index] -> aux(p - 2, q - 2, offset)
            else -> aux(p - 1, q - 1, index)
        }
    }

    return aux(fibs.size - 1, fibs.size)
}


fun main() {
    val lst = listOf(4, 7, 11, 16, 27, 45, 55, 65, 80, 100)
    println(lst.fibContains(4))
    (0..101).filter { lst.fibContains(it) }.forEach{println(it)}
}