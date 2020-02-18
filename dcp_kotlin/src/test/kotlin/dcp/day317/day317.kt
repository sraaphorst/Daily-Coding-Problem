package dcp.day317
// day317.kt
// By Sebastian Raaphorst, 2020.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class GenIntRange: Gen<Pair<Int, Int>> {
    override fun constants(): Iterable<Pair<Int, Int>> = listOf(Pair(0, 0), Pair(0, 1), Pair(1, 1))

    override fun random(): Sequence<Pair<Int, Int>>  = generateSequence {
        val x = Gen.choose(0, 100).random().first()
        val y = Gen.choose(x, 100).random().first()
        Pair(x,y)
    }
}
class PropertyTests: StringSpec() {
    init {
        "Brute force and short method equal" {
            forAll(GenIntRange()) { (x, y) -> andInt(x, y) == andIntBF(x, y)}
        }
    }
}