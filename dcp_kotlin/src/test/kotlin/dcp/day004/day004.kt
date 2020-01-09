package dcp.day004
// day004.kt
// By Sebastian Raaphorst, 2020.


import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1_1() {
        val array = intArrayOf(4, 3, -1, 1)
        val v = findFirstPositiveMissingInteger(array)
        assertEquals(v, 2)
    }

    @Test
    fun example1_2() {
        val array = intArrayOf(4, 3, -1, 1)
        val v = findFirstPositiveMissingIntegerFM(array)
        assertEquals(v, 2)
    }

    @Test
    fun example2_1() {
        val array = intArrayOf(1, 2, 0)
        val v = findFirstPositiveMissingInteger(array)
        assertEquals(v, 3)
    }

    @Test
    fun example2_2() {
        val array = intArrayOf(1, 2, 0)
        val v = findFirstPositiveMissingIntegerFM(array)
        assertEquals(v, 3)
    }

    @Test
    fun example3_1() {
        val array = intArrayOf(1, -2, 3, -1, 0, -4, 2, 5)
        val v = findFirstPositiveMissingInteger(array)
        assertEquals(v, 4)
    }

    @Test
    fun example3_2() {
        val array = intArrayOf(1, -2, 3, -1, 0, -4, 2, 5)
        val v = findFirstPositiveMissingIntegerFM(array)
        assertEquals(v, 4)
    }

    @Test
    fun example4_1() {
        val array = intArrayOf(5, 3, 2, 4, 1)
        val v = findFirstPositiveMissingInteger(array)
        assertEquals(v, 6)
    }

    @Test
    fun example4_2() {
        val array = intArrayOf(5, 3, 2, 4, 1)
        val v = findFirstPositiveMissingIntegerFM(array)
        assertEquals(v, 6)
    }
}

class PropertyTests: StringSpec() {
    init {
        "Find first missing positive integer" {
            forAll(100_000, Gen.list(Gen.choose(-20, 20))) { list ->
                val intArray1 = list.toIntArray()
                val intArray2 = list.toIntArray()
                findFirstPositiveMissingInteger(intArray1) == findFirstPositiveMissingIntegerFM(intArray2)
            }
        }
    }
}