package dcp.day278

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        (0..10).forEach {
            assertEquals(BinaryTree.constructAllTrees(it).size, BinaryTree.catalanNumber(it))
        }
    }
}
