package dcp.day144
// NextHighestContainerTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NextHighestConstantTester {
    @Test
    fun nextHighestConstantTest() {
        val lst = listOf(4, 1, 3, 5, 6)
        val processedLst = preprocess(lst)
        for (i in lst.indices)
            assertEquals(findNextHighestBruteForce(lst, i), findNextHighestConstant(processedLst, i))

    }
}