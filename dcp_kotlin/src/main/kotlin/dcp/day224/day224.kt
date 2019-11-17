package dcp.day224
// day 224.kt
// By Sebastian Raaphorst, 2019.

fun smallestIntegerNotSumInSet(list: List<Int>): Int {
    fun aux(result: Int, idx: Int): Int {
        // If we reach the end of the list, return, and if we find a number
        // that is greater than result because all other numbers will be greater than
        // result and thus sum to something greater than result.
        return if (idx >= list.size || list[idx] > result) result
            else aux(result + list[idx], idx + 1)
    }

    // 1 is the smallest posssible outcome.
    return aux(1, 0)
}
