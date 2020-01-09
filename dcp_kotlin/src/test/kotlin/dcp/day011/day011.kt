package dcp.day011
// day011.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UnitTests {
    val words = listOf("app", "a", "apple", "bumblebee", "bum", "bumble", "cat")
    val badWords = listOf(" ", "ap", "apples", "bumb", "car", "zebra")
    val t = Trie(words)

    @Test
    fun example1() {
        assertTrue { false !in words.map { it in t } }
    }

    @Test
    fun example2() {
        assertTrue { true !in badWords.map { it in t } }
    }

    @Test
    fun example3() {
        assertEquals(t.autocomplete("a").toSet(), setOf("a", "app", "apple"))
    }

    @Test
    fun example4() {
        assertEquals(t.autocomplete("c").toSet(), setOf("cat"))
    }

    @Test
    fun example5() {
        assertEquals(t.autocomplete("bumb").toSet(), setOf("bumblebee", "bumble"))
    }

    @Test
    fun example6() {
        assertEquals(t.autocomplete(" ").toSet(), emptySet())
    }
}