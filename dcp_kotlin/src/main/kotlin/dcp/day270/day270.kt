package dcp.day270
//https://www.dailycodingproblem.com/solution/271?token=1320e1dcfc4aacea2f737b55192b7de940d57e5c3b99fc08636220a94f7590b79aa9061e
import kotlin.math.min

fun List<Int>.fibContains(x: Int): Boolean {
    if (x > last() || x < first())
        return false

    // We are trying to find t.
    // 1. q is the smallest fib # greater than or equal to the size of the array, i.e. fib(q) >= list.length &&
    //      if fib(p) >= list.length, then fib(p) >= fib(q).
    // Let p, q be consecutive numbers.
    //      * If x == array[p], we have found the element.
    //      * If x < appay[p], move p and q down two indices each.
    //      * If x > array[p], move p and q down one each index each, but add an offset of p to the net search term?
    fun fibonacci() =
        generateSequence(Pair(0, 1), { Pair(it.second, it.first + it.second) }).map{ it.first }

    val seq = fibonacci().takeWhile { it <= this.size }.toList()
    val n = seq.size
    var offset = 0
    var p = seq.size - 2
    var q = seq.size - 1

    while (q > 0) {
        val index = min(offset + seq[p], x - 1)
        when {
            x == this[index] -> return true
            x < this[index] -> {
                p -= 2
                q -= 2
            }
            else -> {
                p -= 1
                q -= 1
                offset = index
            }
        }
    }

    return false
}


fun main() {
    val lst = listOf(4, 7, 11, 16, 27, 45, 55, 65, 80, 100)
    (0..101).forEach{println("$it -> ${lst.fibContains(it)}")}
}