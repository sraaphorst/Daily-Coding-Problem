package dcp.day252
// day252.kt
// By Sebastian Raaphorst, 2019.

import java.math.BigInteger


/**
 * All rational numbers of the form 1/a for a >= 2, generated in order.
 */
var ratsGE2 = sequence {
    var i: BigInteger = 2.toBigInteger()
    while (true) {
        yield(Rational(BigInteger.ONE, i))
        i++
    }
}

// Cannot seem to make this tailrec.
fun Rational.toEgyptianRational(): List<Rational> {
    fun aux(r: Rational, iter: Iterator<Rational>, list: List<Rational>): List<Rational>? {
        // If we have reached 0, we are at the end and have our answer.
        if (r.equals(Rational.ZERO))
            return list

        // If the remaining numerator is zero, then we have found the solution and can shortcut.
        if (r.numerator.equals(BigInteger.ONE))
            return list + r

        val n = iter.next()

        // Check if this fits.
        if (r - n >= Rational.ZERO)
           return aux(r - n, iter, list + n)
        else
            return aux(r, iter, list)
    }

    return aux(this, ratsGE2.iterator(), emptyList())!!
}

// Imperative so as to guarantee no stack overflow.
fun Rational.toEgyptianRationalImp(): List<Rational> {
    val gen = ratsGE2.iterator()
    val list = mutableListOf<Rational>()
    var curr = this

    while (curr > Rational.ZERO) {
        // If curr is 1/a, we are done.
        if (curr.numerator == BigInteger.ONE)
            return list + curr

        // Otherwise, check if we can remove the next value from curr.
        val n = gen.next()
        if (curr - n >= Rational.ZERO) {
            curr -= n
            list += n
        }
    }

    return list
}

fun main() {
    val e1 = 815 divBy 934
    //val e2 = e1.toEgyptianRational()
    val e3 = e1.toEgyptianRationalImp()
    println("$e1, $e3")

    val egyptian1 = try {(99 divBy 101).toEgyptianRational()} catch (_: StackOverflowError) { null }
    val egyptian2 = (99 divBy 101).toEgyptianRationalImp()
    println(egyptian1)
    println(egyptian2)
    val sum1 = egyptian1?.fold(Rational.ZERO){acc, curr -> acc + curr}
    val sum2 = egyptian2.fold(Rational.ZERO){acc, curr -> acc + curr}
    println(sum1)
    println(sum2)

}