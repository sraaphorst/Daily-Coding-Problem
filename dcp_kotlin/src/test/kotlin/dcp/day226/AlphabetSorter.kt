package dcp.day226

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AlphabetSorter {
    @Test
    fun alphabetSorted() {
        val words = listOf("xww", "wxyz", "wxyw", "ywx", "ywz")
        assertEquals(findAlphabet(words), listOf('x', 'z', 'w', 'y'))
    }
}
