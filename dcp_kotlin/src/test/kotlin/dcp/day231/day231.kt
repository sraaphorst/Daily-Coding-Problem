package dcp.day231

import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StringInterleaveTester : StringSpec()  {
    init {
        "String interleave tester" {
            forAll(10000) { str: String ->
                val response = interleave(str)
                response == null || checkIfValid(response)
            }
        }
    }
}

class Examples {
    @Test
    fun example1() {
        // This example should fail.
        assertEquals(interleave("aaab"), null)
    }

    @Test
    fun example2() {
        // This has a deterministic answer.
        val response = interleave("aaaabbb")
        assertEquals(response, "abababa")
        assert(response != null)
        if (response != null)
            assert(checkIfValid(response))
    }
}