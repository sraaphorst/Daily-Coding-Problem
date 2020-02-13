package dcp.day312
// day312.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals((0 until 15).map(::coverings), listOf(1, 1, 2, 5, 11, 24, 53, 117, 258, 569, 1255, 2768, 6105, 13465, 29698))
    }
}