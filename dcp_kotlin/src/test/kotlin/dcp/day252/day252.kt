package dcp.day252

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import kotlin.test.assertEquals

class RationalGen: Gen<Rational> {
    override fun constants(): Iterable<Rational> {
        return listOf(Rational.ZERO, Rational.ONE)
    }

    override fun random(): Sequence<Rational> = generateSequence {
        val n = Gen.choose(0, 1_000_000).random().first()
        val d = Gen.choose(n+1, 1_000_000).random().first()
        n divBy d
    }
}


class EgyptianRationalRecursiveTest: StringSpec() {
    init {
        "recursive attempt" {
            forAll(10, RationalGen()) { r ->
                val egyptian = try {r.toEgyptianRational()} catch (_: StackOverflowError) { null }
                val sum = egyptian?.fold(Rational.ZERO){acc, curr -> acc + curr}
                r == sum
            }
        }
    }
}

class EgyptianRationalImperativeTest: StringSpec() {
    init {
        "recursive attempt" {
            forAll(10, RationalGen()) { r ->
                val egyptian = r.toEgyptianRationalImp()
                val sum = egyptian.fold(Rational.ZERO){acc, curr -> acc + curr}
                r == sum
            }
        }
    }
}