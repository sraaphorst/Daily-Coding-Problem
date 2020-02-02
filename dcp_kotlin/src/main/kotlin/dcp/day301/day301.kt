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
    val a = this.rem(b)
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
        require(probability >= 0 && probability <= 1)
        require(m > 0)
        bitSet = BitSet(m)
    }

    val numHashes: Int
        get() = ((m / n) * ln(2.0)).toInt()

    private val hashes = (0 until numHashes).map{murmur3_32(it)}

    val bits: Int
        get() = m

    // Adding an element multiple times makes the error rate unreliable due to increasing numAdds.
    fun add(value: T) {
        val hashedValue = f(value)
        hashes.forEach { bitSet.set(it.hashInt(hashedValue).asInt().rempos(m)) }
    }

    fun check(value: T): InBloomSet {
        println(bitSet)
        println("Num hashes: $numHashes")
        val hashedValue = f(value)
        println("Hashed value: $hashedValue")
        println(hashes.map { it.hashInt(hashedValue).asInt().rempos(m) })
        val indices = hashes.map { bitSet.get(it.hashInt(hashedValue).asInt().rempos(m)) }
        println(indices)
        return if (hashes.all { bitSet.get(it.hashInt(hashedValue).asInt().rempos(m)) }) InBloomSet.MAYBE else InBloomSet.NO
    }
}

fun main() {
    val list = listOf(486618, 364024, 467899, 163726, 715117, 184020, 353694, 842326, 540774, 911759, 427239, 24390, 78219, 520220, 807488, 22075, 206550, 841942, 309462, 285888, 859313, 304488, 638650, 34669, 355925, 528628, 738728, 403247, 661881, 279542, 281083, 410524, 554700, 976806, 238803, 13835, 218786, 297718, 932304, 551380, 972404, 773486, 982089, 948629, 972260, 901656, 808544, 52936, 381703, 462447, 410550, 827053, 261201, 6070, 266460, 677487, 77410, 109461, 944093, 36023, 544909, 442822, 652416, 220435, 56368, 797567, 367408, 249094, 711242, 193255, 319117, 223380, 361131, 576412, 596903, 414969, 109318, 686194, 310895, 551856, 949849, 771654, 678794, 64041, 485685, 165093, 341485, 326459, 682641, 363560, 139440, 730299, 782246, 624132, 328334, 241891, 171101, 488794, 584033, 388515, 214248, 262403, 787553, 76257, 792451, 363370, 777419, 175037, 236807, 589274, 91861, 86144, 659618, 770275, 625278, 226979, 709599, 787916, 153632, 310212, 916086, 182342, 859818, 776210, 687166, 809747, 458626, 150369, 929740, 920270, 252939, 467959, 782395, 643890, 979420, 69523, 947119, 274070, 467123, 448923, 34315, 586576, 524122, 344007, 349486, 788077, 248984, 164044, 491186, 215752, 464111, 30305, 878444, 125443, 999956, 148010, 100449, 661442, 720738, 341648, 92942, 662210, 380445, 811372, 78886, 317726, 738980, 105056, 733178, 103204, 490801, 199892, 76032, 483051, 508804, 121103, 117686, 425836, 385059, 264078, 590019, 10026, 483453, 601919, 366881, 341744, 768839, 993076, 168661, 493547, 587022, 41017, 752641, 373346, 389026, 356061, 872242, 55167, 763819, 138908, 790904, 966228, 929388, 940973, 388923, 726755)
    val bf = BloomFilter<Int>(0.05, list.size)
    println("numHashes: ${bf.numHashes}")
    for (i in list) {
        val old = bf.check(i)
        bf.add(i)
        val new = bf.check(i)
        println("$i -> $old -> $new")
        println(bf.bits)
    }
}
