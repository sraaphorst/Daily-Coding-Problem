package dcp.day307
// day307.kt
// By Sebastian Raaphorst, 2020.

data class BinarySearchTree(val value: Int, val left: BinarySearchTree?, val right: BinarySearchTree?) {
    init {
        require(left == null || left.value < value)
        require(right == null || right.value > value)
    }

    fun floor(x: Int): Int? = when {
        value == x -> x
        value > x && left != null -> left.floor(x)
        value > x && left == null -> null
        value < x && right != null -> right.floor(x) ?: value
        value < x && right == null -> value
        else -> error("Unexpected error")
    }

    fun ceil(x: Int): Int? = when {
        value == x -> x
        value < x && right != null -> right.ceil(x)
        value < x && right == null -> null
        value > x && left != null -> left.ceil(x) ?: value
        value > x && left == null -> value
        else -> error("Unexpected error")
    }

    // Sorted list of all values in the tree through infix traversal.
    fun values(): List<Int> =
        (left?.values() ?: emptyList()) + listOf(value) + (right?.values() ?: emptyList())
}
