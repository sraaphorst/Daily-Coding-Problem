package dcp.day264

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.test.assertTrue

class UnitTests {
    @Test
    fun test1() {
        val db: List<Int> = deBruijn(2, 3)
        println(db)
        assertTrue(covered(2, 3, deBruijn(2, 3)))
    }
}