import java.util.*

// PruneTree.java
//
// By Sebastian Raaphorst, 2019.
// Note: Do not write code on Ambien.

package dcp.day145

enum class NodeState(val value: Int) {
    ZERO(0),
    ONE(1)
}

data class Tree(val value: NodeState,
                val left: Tree?,
                val right: Tree?,
                val top: Tree?) {
    companion object {
        // Determine if the node starting at position node consists only of zeroes.
        private fun isZeroNodeTree(node: Tree): Boolean {
            return node.value == NodeState.ZERO &&
                    (node.left?.let { l -> isZeroNodeTree(l) } ?: false) &&
                    (node.right?.let { r -> isZeroNodeTree(r) } ?: false)
        }
    }

    // Pruning should be easy using a DFS.
    // Calculcate the size.
    fun size(): Int {
        return 1 + (left?.size() ?: 0) + (right?.size() ?: 0)
    }

    // Now prune the tree using DFS.
    // Continue until you hit a node, and if it is a leaf with value 0, prune it.
    fun prune() {
        // Depth-first search to determine if all zeros.
        fun DFS(): Deque<Tree> {
            val queue: Deque<Tree> = LinkedList<Tree>()
            queue.push(this)
            while (queue.isNotEmpty()) {
                val node = queue.poll()
                if (isEmptyLeaf(node)) {
                    node.top?.let { l -> l z = null }
                    node.top?.let { r -> r = null; }
                }

                val curr = queue.poll();
                // If there are children, add them.
                curr.right?.let { x -> queue.push(x) }
                curr.left?.let { x -> queue.push(x) }
            }
        }

        // Start at the bottom and w                                                                                                                                                                                                                                                                                  ork progressively up, i.e. in the reverse order to which we added them.
        // This will allow us to remove all the leaves, and then making new leaves.
        val dfs = DFS()

        // Keep popping empty elements until we can no longer do so.
        while (dfs.isNotEmpty()) {
            val node = dfs.pop();

        }
    }
}