package dcp.day327

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    private fun lr(value: Int, left: Node, right: Node) = Node(value, left, right)
    private fun l(value: Int, left: Node) = Node(value, left, null)
    private fun r(value: Int, right: Node) = Node(value, null, right)
    private fun n(value: Int) = Node(value, null, null)

    @Test
    fun example1() {
        val t1 = lr(1, lr(2, n(4), n(5)), n(3))
        val t2 = lr(6, n(7), r(8, l(9, n(10))))
        val s = lr(7, lr(9, n(4), n(5)), r(11, l(9, n(10))))
        assertEquals(t1 + t2, s)
    }
}
