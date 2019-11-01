package dcp.day202

import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class StringSpecExample : StringSpec() {
    init {
        "Palindrome detection" {
            forAll(100000) { a: Int ->
                // Alternative technique: convert to string, reverse, and compare.
                val isPalindrome = a.toString().reversed() == a.toString()
                a.isPalindrome() == isPalindrome }
        }
    }
}
