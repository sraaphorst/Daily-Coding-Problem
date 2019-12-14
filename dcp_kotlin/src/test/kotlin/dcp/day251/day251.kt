package dcp.day251
// day251.kt
// By Sebastian Raaphorst, 2019.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class RadixSorter : StringSpec() {
    init {
        "Radix sort should sort" {
            forAll(10_000, Gen.list(Gen.choose(0, 1_000_000_000))) { list ->
                radix_sort(list) == list.sorted()
            }
        }
    }
}