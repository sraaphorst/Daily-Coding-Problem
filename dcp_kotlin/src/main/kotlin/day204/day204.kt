package day204
// day204.kt
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


// We need to find a way to traverse the bottom row from either the right or the left.
// The right is preferable because that is where the empty nodes will be so we can detect them
// without traversing the row.
fun <T> countNodes(tree: CBT<T>): Int {
    var current: CBT<T>? = tree

    // Move as far right as we can, keeping track of depth.
    while (current?.right != null) {
        current = current.right
    }

    return 0
}

typealias T = CBT<Int>
fun main() {
    val t = T(5, T(4, T(2), T(1)), T(3))
    println(t.leftDepth())
    println(t.rightDepth())
}