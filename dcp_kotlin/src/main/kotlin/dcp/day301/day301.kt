@file:Suppress("UnstableApiUsage")
package dcp.day301
// day301.kt
// By Sebastian Raaphorst, 2020.

import java.util.*
import com.google.common.hash.Hashing.*
import kotlin.math.ln
import kotlin.math.pow

enum class InBloomSet {
    NO,
    MAYBE
}

private fun Int.rempos(b: Int): Int {
    val a = rem(b)
    return if (a < 0) a + b else a
}

/**
 * The data structure that satisfies the requirements is a a Bloom filter, which is a bit vector and probabilistic
 * in that it tells us with 100% certainty that an element is not in the set, and returns false positives that an
 * element is in the set at a fixed rate.
 *
 * Using a variety of independent, uniformly distributed hash functions, a value is hashed and then checked to see if
 * it is in the set.
 *
 * To add element, hash a number of times, and set the resultant bits.
 *
 * Parameters:
 * n: the number of elements you expect to insert
 * m: the number of bits you will use
 * k: the number of hash functions to use
 *
 * The optimal value for k is (m/n) ln(2).
 * The probability of false positives is (1 - e ^(-kn/m))^k.
 *
 * probability is the probability we want to accept of failed positives.
 * m is the total number of bits allocated for the Bloom filter.
 * n is the expected number of items to be entered.
 * We use Google's implementation of mumumur3_32 in Guava for our family of hash functions.
 */
class BloomFilter<T>(val probability: Double, val n: Int, val f: (T) -> Int = { it.hashCode() }) {
   private val m = (-(n * ln(probability))/(ln(2.0).pow(2))).toInt()
   private val bitSet: BitSet

    init {
        require(n > 0)
        require(probability in 0.0..1.0)
        require(m > 0)
        bitSet = BitSet(m)
    }

    private val numHashes: Int
        get() = ((m / n) * ln(2.0)).toInt()

    private val hashes = (0 until numHashes).map{murmur3_32(it)}

    // Adding an element multiple times makes the error rate unreliable due to increasing numAdds.
    fun add(value: T) {
        val hashedValue = f(value)
        hashes.forEach { bitSet.set(it.hashInt(hashedValue).asInt().rempos(m)) }
    }

    fun check(value: T): InBloomSet {
        val hashedValue = f(value)
        return if (hashes.all { bitSet.get(it.hashInt(hashedValue).asInt().rempos(m)) }) InBloomSet.MAYBE else InBloomSet.NO
    }
}
