package dcp.day204
// dcp.day204.kt
// By Sebastian Raaphorst, 2019.

data class CBT<T>(val value: T, val left: CBT<T>? = null, val right: CBT<T>? = null)

private fun <T> findHeight(tree: CBT<T>?, mover: (CBT<T>?) -> CBT<T>?): Int {
    var height = 0
    var current = tree
    while (mover(current) != null) {
        current = mover(current)
        height += 1
    }
    return height
}

fun <T> countNodes(current: CBT<T>?): Int {
    if (current == null)
        return 0

    val leftHeight = findHeight(current) { it?.left }
    val rightHeight = findHeight(current) { it?.right }
    return if (leftHeight == rightHeight) (2 shl leftHeight) - 1 else countNodes(current.left) + countNodes(current.right) + 1
}
