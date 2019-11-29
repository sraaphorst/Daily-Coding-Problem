package dcp.day237
// day237.kt
// By Sebastian Raaphorst, 2019.

data class Node<T>(val value: T, val children: List<Node<T>> = emptyList()) {
    fun mirror(): Node<T> =
        Node(value, children.reversed().map { it.mirror() })

    fun isSymmetric(): Boolean =
        //this.equals(this, this.mirror())
        this == this.mirror()
}

// This is the equals that we need, but Kotlin generates it for us.
// Type erasure on T is Node is irritating.
fun <T> equals(node1: Node<T>, node2: Node<T>): Boolean =
    node1.value == node2.value
            && node1.children.size == node2.children.size
            && node1.children.zip(node2.children)
        .all{(t1, t2) -> t1.value == t2.value && equals(t1, t2)}
