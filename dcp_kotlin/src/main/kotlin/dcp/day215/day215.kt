package dcp.day215
// day215.kt
// By Sebastian Raaphorst, 2020.

data class BinaryTree(val value: Int, val left: BinaryTree?, val right: BinaryTree?) {
    // Find the horizontal distances at the subtree rooted here.
    fun findHorizontalDistanceView(): List<Int> {
        // We unfortunately use a mutable map here, but since we are doing an infix traversal, it allows us to knock
        // out nodes higher up in the tree in favour of lower nodes in the tree to get the horizontal distance view
        // requested by the question.
        fun aux(distance: Int = 0, node: BinaryTree = this,
                map: MutableMap<Int, Int> = mutableMapOf()): Map<Int, Int> {
            map[distance] = node.value
            if (node.left != null)
                aux(distance - 1, node.left, map)
            if (node.right != null)
                aux(distance +1, node.right, map)
            return map
        }

        // Now turn into a view by sorting by key and getting rid of them.
        return aux().toList().sortedBy { it.first }.unzip().second
    }
}
