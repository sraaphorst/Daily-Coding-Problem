package dcp.day285

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val buildingsEW = listOf(3, 7, 8, 3, 6, 1)
        assertEquals(buildingsEW.unobstructedSunset(), 3)
    }

    @Test
    fun example2() {
        val buildingsEW = listOf(1, 2, 3, 4, 5)
        assertEquals(buildingsEW.unobstructedSunset(), 1)
    }

    @Test
    fun example3() {
        val buildingsEW = listOf(5, 4, 3, 2, 1)
        assertEquals(buildingsEW.unobstructedSunset(), 5)
    }

    @Test
    fun example4() {
        val buildingsEW = listOf(9, 7, 8, 6, 5, 3, 4, 1, 2)
        assertEquals(buildingsEW.unobstructedSunset(), 6)
    }
}