package dcp.day212
// day212.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    // We just make sure that we have a bijection.
    fun columnTest() {
        // This goes from AAAA to ZZZZ
        val upper = 26 * (1 + 26 * (1 + 26 * (1 + 26)))
         for (colNum in 1..upper) {
             //println("$colId -> ${columnNumToId(colId)}, ${columnIdToNum(columnNumToId(colId))}")
             assertEquals(columnIdToNum(columnNumToId(colNum)), colNum)
         }
    }
}