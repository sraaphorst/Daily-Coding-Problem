package dcp.day287
// day287.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val watchers: List<Watcher<Char>> = listOf(
            Pair('a', 1), Pair('a', 3), Pair('a', 5), Pair('b', 2),
            Pair('b', 6), Pair('c', 1), Pair('c', 2), Pair('c', 3),
            Pair('c', 4), Pair('c', 5), Pair('d', 4), Pair('d', 5),
            Pair('d', 6), Pair('d', 7), Pair('e', 1), Pair('e', 3),
            Pair('e', 5), Pair('e', 6))

        val expected = listOf(Pair('a', 'e'), Pair('a', 'c'), Pair('b', 'd'), Pair('c', 'e'), Pair('d', 'e'))
        assertEquals(calculateNMostSimilarShows(5, watchers), expected)
    }
}