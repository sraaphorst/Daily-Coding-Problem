package dcp.day254

data class BinaryTree<out T>(val value: T, val left: BinaryTree<T>?, val right: BinaryTree<T>?) {
    fun toFullTree(): BinaryTree<T>? {
        // Leaf
        return if (left == null && this.right == null)
            BinaryTree(value, null, null)
        // Full tree
        else if (left != null && right != null)
            BinaryTree(value, left.toFullTree(), right.toFullTree())
        // Nodes to be pruned.
        else (left?.toFullTree()) ?: (right?.toFullTree())
    }
}

