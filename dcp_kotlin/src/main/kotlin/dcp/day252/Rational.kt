package dcp.day252

import java.lang.IllegalArgumentException
import java.math.BigInteger

class Rational(n: BigInteger, d: BigInteger): Comparable<Rational> {
    val numerator: BigInteger
    val denominator: BigInteger
    init {
        require(d != BigInteger.ZERO) {"Denominator cannot be zero"}
        val gcd = n.gcd(d)
        numerator = d.signum().toBigInteger() * n / gcd
        denominator = d.signum().toBigInteger() * d / gcd
    }

    operator fun plus(other: Rational): Rational =
        Rational(numerator * other.denominator + denominator * other.numerator, denominator * other.denominator)

    operator fun minus(other: Rational): Rational =
        Rational(numerator * other.denominator - denominator * other.numerator, denominator * other.denominator)

    operator fun times(other: Rational): Rational =
        Rational(numerator * other.numerator, denominator * other.denominator)

    operator fun div(other: Rational): Rational =
        Rational(numerator * other.denominator, denominator * other.numerator)

    operator fun unaryMinus(): Rational =
        Rational(-numerator, denominator)

    override fun compareTo(other: Rational): Int =
        (numerator * other.denominator - other.numerator * denominator).signum()

    override fun toString(): String {
        if (numerator == BigInteger.ZERO) return "0"
        return numerator.toString() + (if (denominator != BigInteger.ONE) "/$denominator" else "")
    }

    override fun hashCode(): Int {
        var result = numerator.hashCode()
        result = 31 * result + denominator.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rational

        if (numerator != other.numerator) return false
        if (denominator != other.denominator) return false

        return true
    }

    // Destructuring components.
    operator fun component1(): BigInteger = numerator
    operator fun component2(): BigInteger = denominator

    companion object {
        val ZERO: Rational = Rational(BigInteger.ZERO, BigInteger.ONE)
        val ONE: Rational = Rational(BigInteger.ONE, BigInteger.ONE)
    }
}

infix fun BigInteger.divBy(other: BigInteger): Rational =
    Rational(this, other)

infix fun Int.divBy(other: Int): Rational =
    Rational(this.toBigInteger(), other.toBigInteger())

infix fun Long.divBy(other: Long): Rational =
    Rational(this.toBigInteger(), other.toBigInteger())

fun String.toRational(): Rational {
    fun fail(): Nothing = throw IllegalArgumentException("Expecting rational in form of n/d or n, received $this")
    if ('/' !in this) {
        val number = toBigIntegerOrNull() ?: fail()
        return Rational(number, BigInteger.ONE)
    }
    val (numerText, denomText) = this.split('/')
    val numer = numerText.toBigIntegerOrNull() ?: fail()
    val denom = denomText.toBigIntegerOrNull() ?: fail()
    return Rational(numer, denom)
}

@kotlin.jvm.JvmName("sumOfBigInteger")
fun Iterable<BigInteger>.sum(): BigInteger =
    this.fold(BigInteger.ZERO){acc, curr -> acc + curr}

@kotlin.jvm.JvmName("sumOfRational")
fun Iterable<Rational>.sum(): Rational =
    this.fold(Rational.ZERO){acc, curr -> acc + curr}
