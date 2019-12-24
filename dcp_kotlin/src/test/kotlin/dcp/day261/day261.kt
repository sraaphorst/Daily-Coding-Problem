package dcp.day261

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class UnitTests {
    private val hc: HuffmanCode
    init {
        val pap = javaClass.classLoader.getResourceAsStream("prideandprejudice.txt")?.bufferedReader()
        require(pap != null)
        val sb = StringBuilder()
        pap.readLines().forEach { sb.append(it).append("\n") }
        hc = HuffmanCode(HuffmanCode.frequencyMap(sb.toString()))
    }

    @Test
    fun test1() {
        assertEquals(hc.decode(hc.encode("Hello")), "Hello")
    }

}
class PropertyTests: StringSpec() {
    init {
        val pap = javaClass.classLoader.getResourceAsStream("prideandprejudice.txt")?.bufferedReader()
        require(pap != null)
        val sb = StringBuilder()
        pap.readLines().forEach { sb.append(it).append("\n") }
        val hc = HuffmanCode(HuffmanCode.frequencyMap(sb.toString()))

        val text = ('a'..'z').toList() + ('A'..'Z').toList() + listOf(" ", ".", ",", "?", "!")
        "Encoding / decoding" {
            forAll(Gen.list(Gen.from(text))) { txt ->
                val s1 = txt.joinToString()
                val s2 = hc.decode(hc.encode(s1))
                s1 == s2
            }
        }

    }
}