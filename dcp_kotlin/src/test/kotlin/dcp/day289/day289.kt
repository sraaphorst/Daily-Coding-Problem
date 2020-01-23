package dcp.day289
// day289.kt
// By Sebastian Raaphorst, 2020.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val l = listOf(3, 4, 5)
        assertEquals(nimMiniMax(l), nimSum(l))
    }

    @Test
    fun example2() {
        val l = listOf(1, 2, 0)
        assertEquals(nimMiniMax(l), nimSum(l))
    }
}


private class HeapGenerator: Gen<List<Int>> {
    override fun constants() = listOf(emptyList(), listOf(1, 1, 1))
    override fun random() = generateSequence {
        val numHeaps = Gen.choose(3, 6).random().first()
        (0 until numHeaps).map { Gen.choose(1, 6).random().first() }
    }
}
class PropertyTests: StringSpec() {
    init {
        "Both misere nim methods return the same value" {
            forAll(100, HeapGenerator()) {heaps ->
                nimMiniMax(heaps) == nimSum(heaps)
            }
        }
    }
}