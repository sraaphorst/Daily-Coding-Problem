package dcp.day301
// day301.kt
// By Sebastian Raaphorst, 2020.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

private class BoundedList: Gen<List<Int>> {
    override fun constants(): List<List<Int>> = emptyList()
    override fun random() = generateSequence {
        val size = Gen.choose(1, 300).random().first()
        Gen.choose(0, 1000).random().take(size).toList()
    }
}

class PropertyTests: StringSpec() {
    init {
        "Filter starts off empty" {
            forAll(1, BoundedList()) { lst ->
                val bf = BloomFilter<Int>(0.1, lst.size)
                lst.all{ bf.check(it) == InBloomSet.NO }
            }
        }
        "Filter returns MAYBE for inserted elements" {
            forAll(5, BoundedList()) { lst ->
                // Make sure each element is in the list after added.
                val bf = BloomFilter<Int>(0.1, lst.size)
                lst.all {
                    bf.add(it)
                    bf.check(it) == InBloomSet.MAYBE
                }
            }
        }
    }
}