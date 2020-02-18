package dcp.day317
// day317.kt
// By Sebastian Raaphorst, 2020.

/**
 * Brute force approach: O(N) time.
 */
fun andIntBF(x: Int, y: Int): Int {
    require(x <= y)
    tailrec
    fun aux(result: Int = x, i: Int = x + 1): Int = when (i) {
        y + 1 -> result
        else -> aux(result and i, i + 1)
    }

    return aux()
}

fun andIntBF(ir: IntRange) = andIntBF(ir.first(), ir.last())

/**
 * We want to find the highest differing bit between x and y.
 * Say x = 111000
 *     y = 111100
 * The highest differing bit is 2^2. Every number between x and y will be anded together
 * to get the result, and thus to get from x to y, every number at and below the highest
 * differing bit will appear:
 * 111000
 * 111001
 * 111010
 * 111011
 * 111100
 * Thus, these positions will all contain a number with a 0 in the bit position, so
 * they will have a 0 in the final result.
 *
 * Thus, we can simply shift both x and y right until they are equal, and then shift them
 * left to get them back to their original positions, at which point, they are the and
 * of all the numbers x to y.
 */
fun andInt(x: Int, y: Int): Int {
    require(x <= y)
    tailrec
    fun aux(i: Int = 0, xs: Int = x, ys: Int = y): Int = when (xs) {
        ys -> xs shl i
        else -> aux(i+1, xs shr 1, ys shr 1)
    }

    return aux()
}

fun main() {
    for (i in 0 until 32)
        for (j in i until 32)
            if (andInt(i, j) != 0)
                println("$i, $j ${andInt(i, j)} ${andIntBF(i, j)}")
}