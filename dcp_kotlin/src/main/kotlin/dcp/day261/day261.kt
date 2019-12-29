package dcp.day261
// day261.kt
// By Sebastian Raaphorst, 2019.

import java.util.Comparator
import java.util.PriorityQueue
import java.util.ArrayDeque
import java.util.Stack

class HuffmanCode(frequencies: Map<Char, Int>) {
    private sealed class Node(val probability: Double): Comparator<Node>, Comparable<Node> {
        override fun compare(o1: Node?, o2: Node?): Int {
            require(o1 != null && o2 != null)
            return when  {
                o1.probability - o2.probability < 0 -> -1
                o1.probability - o2.probability > 0 ->  1
                else                                ->  0
            }
        }

        override fun compareTo(other: Node): Int =
            this.compare(this, other)

        class InternalNode(probability: Double, val left: Node, val right: Node) : Node(probability)
        class Leaf(val char: Char, probability: Double) : Node(probability)
    }

    // The root of the Huffman tree.
    private val root: Node.InternalNode
    private val encoding: Map<Char, List<Int>>

    init {
        // Calculate the total number of samples.
        val samples = frequencies.map { it.value }.sum().toDouble()

        // Create a priority queue and insert a node for each character.
        val pq = PriorityQueue<Node>()
        frequencies.forEach{ (char, frequency) -> pq.add(Node.Leaf(char, frequency.toDouble() / samples))}

        // While there is more than one element left in the priority queue, pull the top two, combine with a parent, and reinsert.
        while (pq.size > 1) {
            val n1 = pq.poll()
            val n2 = pq.poll()
            val n  = Node.InternalNode(n1.probability + n2.probability, n1, n2)
            pq.add(n)
        }

        root = pq.poll() as Node.InternalNode


        // Now traverse the tree to create the encoding map.
        val map = mutableMapOf<Char, List<Int>>()

        val stack = Stack<Pair<Node, List<Int>>>()
        stack.push(Pair(root, emptyList()))
        while (!stack.empty()) {
            val contents = stack.pop()
            val (node, lst) = contents
            when (node) {
                is Node.Leaf -> map[node.char] = lst
                is Node.InternalNode -> {
                    stack.push(Pair(node.left,  lst + listOf(0)))
                    stack.push(Pair(node.right, lst + listOf(1)))
                }
            }
        }
        encoding = map
    }

    fun encode(txt: String): List<Int> =
        txt.mapNotNull { encoding[it] }.flatten()

    fun decode(code: List<Int>): String {
        if (code.isEmpty())
            return ""

        // Traverse the tree.
        val queue = ArrayDeque<Int>(code.size)
        code.forEach { queue.add(it) }

        var txt = ""

        var nodeptr: Node = root

        // Careful here: we have to make sure the last character is processed, which is why we have the while
        // structure here.
        breakable@while (true) {
            if (nodeptr is Node.Leaf) {
                txt += nodeptr.char
                nodeptr = root
                if (queue.isEmpty())
                    break@breakable
            } else if (nodeptr is Node.InternalNode) {
                val elem = queue.pop()
                nodeptr = when (elem) {
                    0 -> nodeptr.left
                    1 -> nodeptr.right
                    else -> throw IllegalArgumentException("Illegal character in encoding: $elem")
                }
            }
        }

        // If we are not at the root now, the encoding was not valid.
        if (nodeptr != root) {
            val codeAsTxt = code.joinToString(transform = Int::toString)
            throw IllegalArgumentException("Invalid code: $codeAsTxt")
        }

        return txt
    }

    companion object HuffmanCode {
        /**
         * Make a map from text for Huffman encoding.
         */
        fun frequencyMap(txt: String): Map<Char, Int> =
            txt.toCharArray().distinct().map{ char -> Pair(char, txt.count { char == it })}.toMap()

        /**
         * Java access.
         */
        @JvmStatic
        fun createCode(txt: String) =
            HuffmanCode(frequencyMap(txt))
    }
}
