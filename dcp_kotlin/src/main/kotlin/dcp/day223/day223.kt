package dcp.day223

enum class Direction {
    MOVING_LEFT,
    MOVING_RIGHT,
    MOVING_UP_FROM_LEFT,
    MOVING_UP_FROM_RIGHT
}

class BinaryTree<T>(val value: T, val left: BinaryTree<T>?, val right: BinaryTree<T>?, var parent: BinaryTree<T>? = null) {
    fun getNthInorderNode(n: Int): BinaryTree<T>? {
        var visited: Int = 0
        var current: BinaryTree<T> = this
        var direction: Direction = Direction.MOVING_LEFT
        while (visited <= n) {
            /**
             * 1. Move left as far as possible. Visit.
             */
            if (direction == Direction.MOVING_LEFT) {
                while (current.left != null)
                    current = current.left!!
                ++visited
                direction = Direction.MOVING_RIGHT
            }

            /**
             * 2. Move right if possible.
             */
            if (direction == Direction.MOVING_RIGHT) {
                if (current.right != null) {
                    current = current.right!!
                    if (current.left != null) {
                        direction = Direction.MOVING_LEFT
                        continue
                    }

                    ++visited

                    if (current.right != null)
                        continue

                    direction = Direction.MOVING_UP_FROM_LEFT
                    continue
                }
            }

            /**
             * Move up from left and visit. Goto 2.
             */
            if (direction == Direction.MOVING_UP_FROM_LEFT) {
                if (current.parent)
            }

        }

    override fun toString(): String {
        return value.toString()
    }
}

fun main() {
    val n3 = BinaryTree(3, null, null)
    val n4 = BinaryTree(4, null, null)
    val n2 = BinaryTree(2, n3, n4)
    n3.parent = n2
    n4.parent = n2

    val n6 = BinaryTree(6, null, null)
    val n5 = BinaryTree(5, null, n6)
    n6.parent = n5

    val n1 = BinaryTree(1, n2, n5)
    n2.parent = n1
    n5.parent = n1

    //println(n1.getNthInorderNode(0)?.value)
    println(n1.getNthInorderNode(1)?.value)

}