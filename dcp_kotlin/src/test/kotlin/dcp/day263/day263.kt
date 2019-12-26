package dcp.day263
// day263.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.*

// There are so many corner cases to test that I could spend all day writing test cases, so we just do a small sample.
class UnitTests {
    @Test
    fun test1() {
        assertFalse(sentenceStateChecker("A."))
    }

    @Test
    fun test2() {
        assertTrue(sentenceStateChecker("The goose flies at night."))
    }

    @Test
    fun test3() {
        assertFalse(sentenceStateChecker("A ."))
    }

    @Test
    fun test4() {
        assertTrue(sentenceStateChecker("Th;."))
    }
}