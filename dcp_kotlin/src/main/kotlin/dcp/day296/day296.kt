package dcp.day296
// day296.kt
// By Sebastian Raaphorst, 2020.

data class BinarySearchTree<T: Comparable<T>>(val value: T, val left: BinarySearchTree<T>?, val right: BinarySearchTree<T>?)

fun <T: Comparable<T>> Array<T>.createBalancedBinarySearchTree(): BinarySearchTree<T>? {
    // Sort the tree.
    val sorted = sortedArray()

    fun midpoint(lower: Int, upper: Int): Int =
        (lower + upper + 1) / 2

    fun aux(lower: Int = 0, upper: Int = size - 1): BinarySearchTree<T>? =
        when {
            lower > upper -> null
            lower == upper -> BinarySearchTree(sorted[lower], null, null)
            else -> {
                val mid = midpoint(lower, upper)
                BinarySearchTree(sorted[mid], aux(lower, mid-1), aux(mid+1, upper))
            }
        }

    return aux()
}
