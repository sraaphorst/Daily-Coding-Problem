package dcp.day252

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals


class RationalGen: Gen<Rational> {
    override fun constants(): Iterable<Rational> {
        return listOf(Rational.ZERO, Rational.ONE)
    }

    override fun random(): Sequence<Rational> = generateSequence {
        val n = Gen.choose(0, 1_000).random().first()
        val d = Gen.choose(n+1, 1_000).random().first()
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

class Examples {
    @Test
    fun example1() {
        assertEquals((4 divBy 13), listOf(1 divBy 4, 1 divBy 18, 1 divBy 468).sum())
    }

    @Test
    fun example2() {
        val r: Rational = Rational(BigInteger("1"), BigInteger("8627546995834"))
        assertEquals(815 divBy 934, listOf(1 divBy 2, 1 divBy 3, 1 divBy 26, 1 divBy 1257, 1 divBy 1695833, r).sum())
    }
}