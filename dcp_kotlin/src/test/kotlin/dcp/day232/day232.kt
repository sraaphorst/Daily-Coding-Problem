package dcp.day232

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Examples {
    @Test
    fun example1() {
        val xyz: PrefixMapSum = PrefixMapSum()
        xyz.insert("columnar", 3)
        assertEquals(xyz.sum("co"), 3)
    }

    @Test
    fun example2() {
        val xyz: PrefixMapSum = PrefixMapSum()
        xyz.insert("column", 2)
        assertEquals(xyz.sum("column"), 2)
    }

    @Test
    fun example3() {
        val xyz: PrefixMapSum = PrefixMapSum()
        xyz.insert("columnar", 3)
        assertEquals(xyz.sum("col"), 3)

        xyz.insert("column", 2)
        assertEquals(xyz.sum("column"), 5)
    }

    @Test
    fun example4() {
        val xyz: PrefixMapSum = PrefixMapSum()
        xyz.insert("c", 1)
        xyz.insert("co", 2)
        xyz.insert("ca", 3)
        xyz.insert("calinthrope", 4)
        xyz.insert("cog", 5)
        xyz.insert("cephalopod", 6)

        assertEquals(xyz.sum("c"), 21)
        assertEquals(xyz.sum("co"), 7)
        assertEquals(xyz.sum("ca"), 7)
        assertEquals(xyz.sum("ce"), 6)
        assertEquals(xyz.sum("a"), 0)
    }
}