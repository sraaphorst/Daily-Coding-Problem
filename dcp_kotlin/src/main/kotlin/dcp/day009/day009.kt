package dcp.day009
// day009.kt
// By Sebastian Raaphorst, 2020.

import kotlin.math.max

/**
 * For this list of Int of size n, find the maximum sum of elements such that none of the elements are adjacent.
 * For each element in the list, we consider the case of including it or omitting it by maintaining variables
 * exclusive and inclusive, which keep track of the maximum sum of the sublists as we go.
 *
 * Thus, we manage to accomplish this in O(n) with constant space.
 */
fun List<Int>.findMaxSumOfNonAdjacentElements(): Int {
    if (isEmpty())
        return 0

    tailrec
    fun aux(idx: Int = 1, inclusive: Int = first(), exclusive: Int = 0): Int =
        if (idx == size)
            max(inclusive, exclusive)
        else
            // We may not always want to add the current element to inclusive, because it might be negative.
            aux(idx + 1, max(inclusive, exclusive + get(idx)), max(inclusive, exclusive))

    return aux()
}