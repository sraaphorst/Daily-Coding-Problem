package dcp.day247
// day247.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.abs
import kotlin.math.max

data class BinaryTree<T>(val value: T, val left: BinaryTree<T>? = null, val right: BinaryTree<T>? = null) {
    fun height(): Int =
        1 + max(left?.height()?: 0, right?.height() ?: 0)

    fun balanced(): Boolean =
        abs((left?.height() ?: 0) - (right?.height() ?: 0)) <= 1 && (left?.balanced() ?: true) && (right?.balanced() ?: true)
}