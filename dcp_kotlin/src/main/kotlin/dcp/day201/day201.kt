package dcp.day201
// day201.kt
// By Sebastian Raaphorst

import kotlin.math.max

// The problem is really represented by an upper triangle of a matrix.
fun maximumWeightPath(lists: List<MutableList<Int>>): Int {
    val errorMessage: (Int) -> String = { "row $it must have length ${it + 1}" }

    if (lists.isEmpty())
        return 0

    if (lists.size == 1) {
        require(lists[0].size == 1) { errorMessage(0) }
        return lists[0][0]
    }

    // It makes more sense to start at the bottom of the triangle and work your way towards the top, as then
    // you can do so in time O(n) and space O(1). We could do this functionally in space O(n), but we aim for
    // efficiency in this particular solution.

    // Skip the last row, as every node contains the value of the maximum path passing through that node.
    for (row in lists.size-2 downTo 0) {
        require(lists[row].size == row + 1) { errorMessage(row) }
        for (col in lists[row].indices) {
            // Calculate the maximum path weight passing through this node.
            // It includes the weight of this node and the maximum path weight passing through one of its vertically
            // adjacent nodes.
            lists[row][col] = lists[row][col] + max(lists[row + 1][col], lists[row + 1][col + 1])
        }
    }

    // Now the top number is the maximum weight path.
    return lists[0][0]
}


