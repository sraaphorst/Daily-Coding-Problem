package dcp.day307
// day307.kt
// By Sebastian Raaphorst, 2020.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec


// Find the floor of an element in a sorted list. We will use this in testing.
fun List<Int>.floor(x: Int): Int? = when {
    isEmpty() || first() > x -> null
    else -> drop(1).floor(x) ?: first()
}

// Find the ceil of an element in a sorted list. We will use this in testing.
fun List<Int>.ceil(x: Int): Int? = when {
    isEmpty() || last() < x -> null
    else -> dropLast(1).ceil(x) ?: last()
}

// To make a Gen<BinarySearchTree>.
class MutableBinarySearchTree(val value: Int, var left: MutableBinarySearchTree?, var right: MutableBinarySearchTree?) {
    fun insert(x: Int) {
        when {
            x < value && left == null -> left = MutableBinarySearchTree(x, null, null)
            x < value && left != null -> left!!.insert(x)
            x > value && right == null -> right = MutableBinarySearchTree(x, null, null)
            x > value && right != null -> right!!.insert(x)
            else -> Unit
        }
    }

    fun toBinarySearchTree(): BinarySearchTree =
        BinarySearchTree(value,
            if (left == null) null else left!!.toBinarySearchTree(),
            if (right == null) null else right!!.toBinarySearchTree())
}

// A generator for binary search trees.
class BinarySearchTreeGen: Gen<BinarySearchTree> {
    override fun constants(): Iterable<BinarySearchTree> = listOf(BinarySearchTree(0, null, null))

    override fun random(): Sequence<BinarySearchTree> = generateSequence {
        val size = Gen.choose(1, 100).random().first()
        val list = Gen.choose(0, 1000).random().take(size)
        val msb = MutableBinarySearchTree(list.first(), null, null)
        (1 until size).forEach(msb::insert)
        msb.toBinarySearchTree()
    }
}

class PropertyTests: StringSpec() {
    init {
        "Make sure floor and ceil coincide for lists and trees" {
            forAll(BinarySearchTreeGen()) { t ->
                val values = t.values()
                (0 until 1000).all { Triple(it, t.floor(it), t.ceil(it)) == Triple(it, values.floor(it), values.ceil(it)) }
            }
        }
    }
}
