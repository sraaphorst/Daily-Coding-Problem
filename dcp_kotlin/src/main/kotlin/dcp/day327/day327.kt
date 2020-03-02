package dcp.day327

data class Node(val value: Int, val left: Node?, val right: Node?)

operator fun Node?.plus(n: Node?): Node? = when {
    this == null && n == null -> null
    this != null && n == null -> this
    this == null && n != null -> n
    this != null && n != null -> Node(value + n.value, left.plus(n.left), right.plus(n.right))
    else -> null
}