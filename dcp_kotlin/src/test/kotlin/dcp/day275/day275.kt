package dcp.day275
// day275.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val expected: List<String> = listOf(
            "1",
            "11",
            "21",
            "1211",
            "111221",
            "312211",
            "13112221",
            "1113213211",
            "31131211131221",
            "13211311123113112211"
        )

        assertEquals(lookAndSay().take(10).toList(), expected)
    }
}