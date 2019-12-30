package dcp.day258

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// day258.kt
// By Sebastian Raaphorst, 2019.

class UnitTests {
    @Test
    fun example1() {
        val t = Node(1,
            Node(2, Node(4, null, null), Node(5, null, null)),
            Node(3, Node(6, null, null), Node(7, null, null))
        )
        assertEquals(t.toBoustrophedonOrder(), listOf(1, 3, 2, 4, 5, 6, 7))
    }
}
