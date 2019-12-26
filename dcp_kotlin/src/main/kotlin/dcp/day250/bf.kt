package dcp.day250

import java.util.LinkedList

interface Circular<T> : Iterable<T> {
    fun state(): T
    fun inc()
    fun isZero(): Boolean   // `true` in exactly one state
    fun hasNext(): Boolean  // `false` if the next state `isZero()`

    override fun iterator() : Iterator<T> {
        return object : Iterator<T> {
            var started = false

            override fun next(): T {
                if(started) {
                    inc()
                } else {
                    started = true
                }

                return state()
            }

            override fun hasNext() = this@Circular.hasNext()
        }
    }
}

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

abstract class CircularList<E, H: Circular<E>>(val size: Int) : Circular<List<E>> {
    protected abstract val state: List<H>  // state.size == size

    override fun inc() {
        state.forEach {
            it.inc()
            if(! it.isZero()) return
        }
    }

    override fun isZero() = state.all {it.isZero()}
    override fun hasNext() = state.any {it.hasNext()}
}

abstract class IntCombinations(size: Int) : CircularList<Int, Ring>(size)

class BinaryBits(N: Int) : IntCombinations(N) {
    override val state = Array(N, {Ring(2)}).toList()
    override fun state() = state.map {it.state()}.reversed()
}

class Permutations(N: Int) : IntCombinations(N) {
    override val state = mutableListOf<Ring>()

    init {
        for(i in N downTo 1) {
            state += Ring(i)
        }
    }

    override fun state(): List<Int> {
        val items = (0..size - 1).toCollection(LinkedList())
        return state.map {ring -> items.removeAt(ring.state())}
    }
}

fun <T> `⊃`(set1: Set<T>, set2: Set<T>): Set<T> =
    set1.intersect(set2)

fun bruteForceCryptopuzzle(word1: String, word2: String, wordSum: String): Map<Char, Int>? {
    val chars = (word1 + word2 + wordSum).toCharArray().distinct()
    for (configuration in Permutations(10)) {
        val map = chars.zip(configuration).toMap()
        val n1 = word1.fold(word1){acc, char -> acc.replace(char.toString(), map[char].toString())}.toInt()
        val n2 = word2.fold(word2){acc, char -> acc.replace(char.toString(), map[char].toString())}.toInt()
        val nSum = wordSum.fold(wordSum){acc, char -> acc.replace(char.toString(), map[char].toString())}.toInt()

        if (n1 + n2 == nSum)
            return map
    }
    return null
}

fun main() {
    println(bruteForceCryptopuzzle("SEND", "MORE", "MONEY"))
}