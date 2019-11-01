package dcp.day204
// dcp.day204.kt
// By Sebastian Raaphorst, 2019.

data class CBT<T>(val value: T, val left: CBT<T>? = null, val right: CBT<T>? = null) {
    // We need a function that takes a CBT<T>? and moves to a CBT<T>?
    private fun aux(depth: Int, mover: () -> CBT<T>?): Int {
        val next = mover()
        return if (next != null) {
            aux(depth + 1, mover)
        } else {
            depth
        }
    }

    fun leftDepth(): Int {
        return aux(1) { this.left }
    }

    fun rightDepth(): Int {
        return aux(1) { this.right }
    }
}

typealias T = CBT<Int>


fun <T> findLefHeight(tree: CBT<T>?): Int {
    var height = 0
    var current = tree
    while (current != null) {
        current = current.left
        height += 1
    }
    return height
}

fun <T> findRightHeight(tree: CBT<T>?): Int {
    var height = 0
    var current = tree
    while (current != null) {
        current = current.right
        height += 1
    }
    return height
}

// We need to find a way to traverse the bottom row from either the right or the left.
// The right is preferable because that is where the empty nodes will be so we can detect them
// without traversing the row.
fun <T> countNodes(tree: CBT<T>?): Int {
    var current: CBT<T>? = tree

    val leftHeight = findLefHeight(current)
    val rightHeight = findRightHeight(tree)
    return if (leftHeight == rightHeight) (2 shl leftHeight - 1) else countNodes(tree?.left) + countNodes(tree?.right) + 1
}
