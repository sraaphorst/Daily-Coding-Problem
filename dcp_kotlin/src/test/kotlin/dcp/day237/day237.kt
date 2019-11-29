package dcp.day237

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Examples {
    @Test
    fun example1() {
        val t1 = Node(
            4, listOf(
                Node(3, listOf(Node(9))),
                Node(4),
                Node(3, listOf(Node(9)))
            )
        )
        assertEquals(t1.isSymmetric(), true)
    }

    @Test
    fun example2() {
        assertEquals(Node(0).isSymmetric(), true)
    }

    @Test
    fun example3() {
        val t2 = Node(
            4, listOf(
                Node(3, listOf(Node(9))),
                Node(4),
                Node(3, listOf(Node(8)))
            )
        )
        assertEquals(t2.isSymmetric(), false)
    }

    @Test
    fun example4() {
        val t3 = Node(0, listOf(Node(1), Node(2)))
        assertEquals(t3.isSymmetric(), false)
    }
}