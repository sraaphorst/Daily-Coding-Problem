package dcp.day258

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

fun main() {
    val t = Node(1,
        Node(2, Node(4, null, null), Node(5, null, null)),
        Node(3, Node(6, null, null), Node(7, null, null)))
    println(t.toBoustrophedonOrder())
}