package dcp.day228
// day228.kt
// By Sebastian Raaphorst, 2019.

import java.math.BigInteger
import java.util.LinkedList

// https://medium.com/@voddan/a-handful-of-kotlin-permutations-7659c555d42

// Typeclass that circulates over its members.
interface Circular<T> : Iterable<T> {
    fun state(): T
    fun inc()
    fun isZero(): Boolean   // `true` in exactly one state
    fun hasNext(): Boolean  // `false` if the next state `isZero()`

    override fun iterator() : Iterator<T> {
        return object : Iterator<T> {
            var started = false

            override fun next(): T {
                if(started)
                    inc()
                else
                    started = true

                return state()
            }

            override fun hasNext() = this@Circular.hasNext()
        }
    }
}

// Implementation of Circular for Int.
class Ring(val size: Int) : Circular<Int> {
    private var state = 0

    override fun state() = state
    override fun inc() {state = (1 + state) % size}
    override fun isZero() = (state == 0)
    override fun hasNext() = (state != size - 1)

    init {
        assert(size > 0)
    }
}

/**
 * Typeclass that circulates over combinations of its members.
 * We circulate over lists of E using the Circular typeclass H for E.
 */
abstract class CircularList<E, H: Circular<E>>(val size: Int) : Circular<List<E>> {
    protected abstract val state: List<H>

    override fun inc() {
        state.forEach {
            it.inc()
            if(! it.isZero()) return
        }
    }

    override fun isZero() = state.all {it.isZero()}
    override fun hasNext() = state.any {it.hasNext()}
}

/**
 * Typeclass for circulating over combinations of Int using a Ring.
 */
abstract class IntCombinations(size: Int) : CircularList<Int, Ring>(size)

/**
 * Specific typeclass for cycling over all permutations of N integers.
 */
class Permutations(N: Int) : IntCombinations(N) {
    override val state = mutableListOf<Ring>()

    init {
        /**
         * N possibilities for the first position, N-1 for the second, etc.
         */
        for(i in size downTo 1) {
            state += Ring(i)
        }
    }

    override fun state(): List<Int> {
        val items = (0 until size).toCollection(LinkedList())
        return state.map {ring -> items.removeAt(ring.state())}
    }
}


/**
 * Convert the numbers to strings and sort them.
 * Then iterate over every permutation of the number of strings and concatenate them in that order.
 * Reconvert them to integers and return the maximum one.
 * This is a brute force operation of time O(n!).
 */
fun bruteForceNumber(numbers: List<Int>): BigInteger {
    val n = numbers.size
    if (n == 0)
        return BigInteger.ZERO
    if (n == 1)
        return numbers.first().toBigInteger()

    val strings = numbers.map{ it.toString() }
    return Permutations(numbers.size).map { p ->
        (0 until n).map{ strings[p[it]] }.joinToString (separator = ""){ it }
    }.map { it.toBigInteger() }.max() ?: BigInteger.ZERO
}

fun comparatorNumber(numbers: List<Int>): BigInteger {
    if (numbers.isEmpty())
        return BigInteger.ZERO
    val strs = numbers.map { it.toString() }
    val maxLength = strs.map { it.length }.max() ?: 0
    if (maxLength == 0)
        return BigInteger.ZERO

    val normalized = strs.map {
        val numRepeats = maxLength / it.length + 1
        it.repeat(numRepeats).take(maxLength)
    }

    val ordered = normalized.zip(numbers).sortedBy { it.first }.reversed()
    return ordered.joinToString(separator=""){it.second.toString()}.toBigInteger()

}
