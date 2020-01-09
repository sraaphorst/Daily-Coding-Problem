package dcp.day278

data class BinaryTree(val left: BinaryTree?, val right: BinaryTree?) {
    companion object {
        // Memoize for efficiency.
        private val cache: MutableMap<Int, List<BinaryTree>> = mutableMapOf()

        fun constructAllTrees(numNodes: Int): List<BinaryTree> {
            // Boundary cases: there are 0 trees of size 0.
            if (numNodes == 0)
                return emptyList()

            // There is one node of size 1.
            if (numNodes == 1)
                return listOf(BinaryTree(null, null))

            // Create a node and then append all combinations of i in [0, x-1] trees on the left and
            // j = x - 1 - i trees on the right.
            // All possible left trees.
            return (0 until numNodes).flatMap { leftNodes ->
                val rightNodes = numNodes - 1 - leftNodes

                val leftSubtrees = cache[leftNodes] ?: run {
                    val trees = constructAllTrees(leftNodes)
                    cache[leftNodes] = trees
                    trees
                }

                val rightSubtrees = cache[rightNodes] ?: run {
                    val trees = constructAllTrees(rightNodes)
                    cache[rightNodes] = trees
                    trees
                }

                // We need special cases when the left or right subtrees contain 0 nodes, as they must be null.
                // We have handled the corner
                when {
                    leftNodes == 0 -> rightSubtrees.map { rightSubtree -> BinaryTree(null, rightSubtree) }
                    rightNodes == 0 -> leftSubtrees.map { leftSubtree -> BinaryTree(leftSubtree, null) }
                    else -> leftSubtrees.flatMap { leftSubtree ->
                        rightSubtrees.map { rightSubtree ->
                            BinaryTree(leftSubtree, rightSubtree)
                        }
                    }
                }
            }
        }

        // The number of BSTs over n nodes should equal the nth Catalan number.
        // We calculate it using dynamic programming.
        fun catalanNumber(n: Int): Int {
            if (n <= 1)
                return n

            val catalan: MutableList<Int> = MutableList(n + 1){0}
            catalan[0] = 1
            catalan[1] = 1

            (2..n).forEach { i ->
                (0 until i).forEach {j ->
                    catalan[i] += catalan[j] * catalan[i - j - 1]
                }
            }

            return catalan[n]
        }
    }
}
