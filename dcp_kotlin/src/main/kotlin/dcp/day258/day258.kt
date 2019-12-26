package dcp.day258
// day258.kt
// By Sebastian Raaphorst, 2019.

enum class Direction {
    LEFT, RIGHT
}

data class Node<T>(val value: T, val left: Node<T>?, val right: Node<T>?) {
    fun toBoustrophedonOrder(): List<T> {
        fun aux(row: List<Node<T>>, dir: Direction): List<T> {
            if (row.isEmpty()) return emptyList()
            val newRow = row.map { listOfNotNull(it.left, it.right) }.flatten()
            val newDir = if (dir == Direction.LEFT) Direction.RIGHT else Direction.LEFT
            return (if (dir == Direction.LEFT) row.map(Node<T>::value).asReversed() else row.map(Node<T>::value)) + aux(newRow, newDir)
        }

        return aux(listOf(this), Direction.RIGHT)
    }
}
