package dcp.day310
// day310.kt
// By Sebastian Raaphorst, 2020.

import kotlin.math.floor
import kotlin.math.log2
import kotlin.math.pow

/**
 * This is sequence A000788 in the Online Encyclopedia of Integer Sequences:
 * http://oeis.org/A000788
 * It has a number of closed forms. We implement two below.
 */

/**
 * Brute force: count the 1s in the string representations. Assuming the conversion to string takes O(n),
 * this runs in time O(n).
 */
fun binaryCountViaStrings(n: Int): Int {
    require(n >= 0)
    return (1..n).map(Integer::toBinaryString).map { it.count { c -> c == '1' }}.sum()
}

/**
 * Count the number of 1s in the binary representation of [0,n].
 * Should work in time O(log n). We can remove the recursion to prevent stack overflow but will then need mutability.
 */
fun binaryCount(n: Int): Int {
    require(n >= 0)
    if (n == 0)
        return 0

    val k = floor(log2(n.toDouble()))
    val m = (2.toDouble().pow(k) - 1).toInt()
    return (k * (m + 1) / 2).toInt() + binaryCount(n - m - 1) + n - m
}

/**
 * Another formula for counting the number of 1s in the binary representation [0,n].
 * Should work in time O(log n).
 */
fun binaryCount2(n: Int): Int = when {
    n < 0 -> error("n must be greater than 0: $n")
    n == 0 -> 0
    n % 2 == 0 -> {
        val np = n / 2
        binaryCount2(np) + binaryCount2(np - 1) + np
    }
    else -> {
        val np = (n - 1) / 2
        2 * binaryCount2(np) + np + 1
    }
}
