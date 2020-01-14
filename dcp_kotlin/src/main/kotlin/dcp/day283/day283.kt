package dcp.day283
// day283.kt
// By Sebastian Raaphorst, 2020.

import java.util.PriorityQueue

// Generate the sequence of all normal aka 5-smooth numbers.
// May want to switch this to BigInteger to allow higher values.
val regularNumbers = sequence {
    // All numbers are of the form 2^i 3^j 5^k
    val pq = PriorityQueue<Int>()
    pq.add(1)
    while (true) {
        val top = pq.first() ?: throw RuntimeException("Illegal state in regular number generator.")
        yield(top)
        while (pq.isNotEmpty() && pq.first() == top)
            pq.remove()

        pq.addAll(listOf(2*top, 3*top, 5*top))
    }
}

fun firstNRegularNumbers(n: Int): List<Int> =
    regularNumbers.take(n).toList()
