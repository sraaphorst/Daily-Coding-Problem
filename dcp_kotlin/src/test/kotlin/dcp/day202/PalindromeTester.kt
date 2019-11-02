package dcp.day202
// PalindromeTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PalindromeTester {
    @Test
    fun palTest() {
        assertEquals(101.isPalindrome(), true)
        assertEquals(1011.isPalindrome(), false)
        assertEquals(111.isPalindrome(), true)
        assertEquals(0.isPalindrome(), true)
        assertEquals(10.isPalindrome(), false)
    }
}