package dcp.day284
// day284.kt
// By Sebastian Raaphorst, 2020.

typealias Depth = Int
typealias ParentChild = Pair<Int, Int>
typealias Cousins = Pair<Int, Int>
typealias DepthInfo = Map<Depth, List<ParentChild>>

data class BinaryTree(val value: Int, val left: BinaryTree?, val right: BinaryTree?) {
    // Find all cousins: cousins are, by definition, on the same level of the tree but have different parents.
    fun findAllCousins(): Set<Cousins> {
        // Start by collecting all nodes in the tree in a map depth -> (parentvalue -> nodevalue).
        // We skip the root node since it cannot have any cousins.
        tailrec
        fun aux(
            nodes: List<Pair<Depth, BinaryTree>> = listOf(1 to this),
            depthMap: DepthInfo = emptyMap()
        ): DepthInfo {
            if (nodes.isEmpty())
                return depthMap.filter{ it.value.isNotEmpty() }

            // Process the first node.
            val node = nodes.first()
            val (depth, parent) = node
            val children = listOfNotNull(parent.left, parent.right)
            val newDepthMap = mergeDepthInfo(depthMap, mapOf(depth to children.map { parent.value to it.value }))
            return aux(nodes - node + children.map { depth + 1 to it }, newDepthMap)
        }

        // This results in a map, indexed by depth, of pairs of the form (parent, child).
        // We drop the depth since it is no longer necessary to get slices of children from the same generation
        // with their parents. We then iterate over each generation and find the cousins.
        // We iterate over each depth slice and match together
        val depthInfo = aux().values

        // Now we process across each layer of the tree,
        return depthInfo.flatMap{ findCousins(it) }.toSet()
    }

    companion object {
        /**
         * Given two maps whose elements are lists, return the combined map where the elements are the concatenations
         * of the lists.
         */
        private fun mergeDepthInfo(m1: DepthInfo, m2: DepthInfo): DepthInfo =
            (m1.keys + m2.keys).map {
                val a1 = m1[it] ?: emptyList()
                val a2 = m2[it] ?: emptyList()
                it to (a1 + a2)
            }.toMap()

        /**
         * Find all the cousins within a generation, where the generation is represented by a list of entries
         * of the form Pair<Parent.value, Child.value>. We return the cousins as pairs in lexicographical order.
         */
        private fun findCousins(generation: List<ParentChild>): List<Cousins> =
            generation.flatMap { pc1 ->
                generation.filter { pc2 -> pc1.first < pc2.first }
                    .map { pc2 -> if (pc1.second < pc2.second) Pair(pc1.second, pc2.second) else Pair(pc2.second, pc1.second)}}
    }
}
