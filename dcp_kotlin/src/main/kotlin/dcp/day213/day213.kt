package dcp.day213
// day213.kt
// By Sebastian Raaphorst, 2019.


// The Ts are just to describe the bottom layer. In the example, they are Int.
abstract class HDTree<T>(
    open val value: T,
    open val depth: Int,
    open val parent: HDTree<T>?,
    val left: HDTree<T>?,
    val right: HDTree<T>?)

abstract class HDNode<T>(t: T,
                         final override val parent: HDTree<T>? = null,
                         leftValue: T?,
                         rightValue: T?) : HDTree<T> {
    override val value: T = t
    override val depth = if (parent == null) 0 else parent.depth + 1
    override val left = if (leftValue == null) ?
}
//
//    val value: T, val leftNode: HDTree<T>?, val rightNode: HDTree<T>?) extends HDTree<T>(val depth: Int) {
//    override val depth: Int
//}
//
//class HDRoot<T>(val value: T, leftNode: HDTree<T>?, rightNode: HDTree<T>?) {
//    val depth: Int = 0
//    val parent: HDTree<T>? = null
//    val left: HDTree<T>? = leftNode
//    val right: HDTree<T>? = rightNode
//}

open class Base(p: Int)

class Derived(p: Int) : Base(p)