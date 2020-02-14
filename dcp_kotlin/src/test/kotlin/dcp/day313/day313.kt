package dcp.day313

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.jupiter.api.Test

class UnitTests {
    @Test
    fun example1() {
        assertEquals(unlock(456, emptyList()), 4 + 5 + 4)
    }

    @Test
    fun example2() {
        // Force to go through 565 to get to 545, i.e. do down on the middle digit instead of up, requiring
        // two extra moves.
        assertEquals(unlock(545, listOf(445, 645, 535, 544, 546)), 5 + 5 + 6)
    }

    @Test
    fun example3() {
        // Force a single path 000 to 008, then 008 to 088, and finally 088 to 888, for 24 moves.
        val permissible = listOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            18, 28, 38, 48, 58, 68, 78, 88,
            188, 288, 388, 488, 588, 688, 788, 888)
        assertEquals(unlock(888, (0 until 1000) - permissible), permissible.size - 1)
    }

    @Test
    fun example4() {
        // Impossible to get to: 555
        val forbidden = listOf(
            554, 556, 545, 565, 455, 655
        )
        assertNull(unlock(555, forbidden))
    }

    @Test
    fun example5() {
        // Block between 000 and 002 by forbidding 001. Going around costs 4 instead of the usual 2 it would take.
        assertEquals(unlock(2, listOf(1)), 4)
    }
}