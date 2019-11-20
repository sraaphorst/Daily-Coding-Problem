package dcp.day226

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AlphabetSorter {
    @Test
    fun alphabetSorted1() {
        val words = listOf("xww", "wxyz", "wxyw", "ywx", "ywz")
        assertEquals(findAlphabet(words), listOf('x', 'z', 'w', 'y'))
    }

    @Test
    fun alphabetSorted2() {
        val words = listOf("caa", "aaa", "aab")
        assertEquals(findAlphabet(words), listOf('c', 'a', 'b'))
    }

    @Test
    fun alphabetSorted3() {
        val words = listOf("wrt", "wrf", "er", "ett", "rftt")
        assertEquals(findAlphabet(words), listOf('w', 'e', 'r', 't', 'f'))
    }
}
