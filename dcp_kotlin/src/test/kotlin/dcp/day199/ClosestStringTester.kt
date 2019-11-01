package dcp.day199
// ClosestStringTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// Based on the rules we have implemented, this should be the final results.
class NextHighestConstantTester {
    @Test
    fun nextHighestConstantTest() {
        assertEquals(closestString("(()"), "(())")
        assertEquals(closestString("))()("), "()()()()")
        assertEquals(closestString(""), "")
        assertEquals(closestString(")"), "()")
        assertEquals(closestString("("), "()")
        assertEquals(closestString("()"), "()")
        assertEquals(closestString(")("), "()()")
        assertEquals(closestString(")))))()((((("), "()()()()()()((((()))))")
    }
}