package dcp.day266
// day266.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val expected = listOf("appeal", "dapple", "lapped", "rappel", "apples", "lappet", "applet")
        val dictionary = expected + listOf("zebra", "apple", "potato", "missile", "ignorance", "jumper")
        val stepWords = findStepWords("apple", dictionary)
        assertEquals(stepWords, expected)
    }
}
