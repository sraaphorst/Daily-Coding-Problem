package dcp.day251
// day251.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.ceil
import kotlin.math.log10
import kotlin.math.pow

infix fun Int.`**`(exponent: Int): Int = toDouble().pow(exponent.toDouble()).toInt()

/**
 * Radix sort.
 */
fun radix_sort(list: List<Int>): List<Int> {
    if (list.isEmpty())
        return emptyList()

    val m = ceil(log10(list.max()!!.toDouble())).toInt()

    fun aux(list: List<Int>, base: Int): List<Int> {
        if (base >= m)
            return list
        val pos = 10 `**` base
        // Sort on the base-th digit, starting from the right (calling that position 0) and working left.
        val newList = list.groupBy { (it / pos) % (10 * pos)  }.toList().sortedBy { it.first }.map{ it.second }.flatten()
        return aux(newList, base + 1)
    }
    return aux(list, 0)
}
