package dcp.day282
// day282.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UnitTests {
    @Test
    fun example1() {
        assertEquals((0..5).toList().findPythagoreanTriplet(), Triplet(3, 4, 5))
    }

    @Test
    fun example2() {
        assertEquals((4..13).toList().findPythagoreanTriplet(), Triplet(5, 12, 13))
    }

    // Cannot have a negative number in a Pythagorean triple.
    @Test
    fun example3() {
        assertNull((4..13).map { -it }.findPythagoreanTriplet())
    }

    @Test
    fun example4() {
        assertNull((0..4).toList().findPythagoreanTriplet())
    }
}