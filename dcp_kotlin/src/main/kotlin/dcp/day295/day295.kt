package dcp.day295
// day295.kt
// By Sebastian Raaphorst, 2020.

fun pascalRow(k: Int): List<Int> {
    if (k == 0)
        return listOf(1)

    // We need to calculate 1!, 2!, ..., k!, which can clearly be stored in O(k).
    tailrec
    fun factCalculator(i: Int, curr: Int, factList: List<Int>): List<Int> =
        when (i) {
            k+2 -> factList
            else -> factCalculator(i+1, curr * i, factList + curr)
        }

    val fact = factCalculator(1, 1, emptyList())
    return (0..k).map { i -> fact[k] / fact[i] / fact[k-i] }
}
