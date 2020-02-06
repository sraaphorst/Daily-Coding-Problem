package dcp.day306
// day306.kt
// By Sebastian Raaphorst, 2020.

import java.util.*
import kotlin.math.min

fun <T: Comparable<T>> List<T>.sortPartiallySorted(k: Int): List<T> {
    if (isEmpty())
        return this

    val minVal = min(size, k+1)

    // Make a copy of the original list that is var.
    val list = mutableListOf<T>()
    list.addAll(this)
    val sortedList = MutableList<T?>(size){null}

    // In order to do this, we have to use a heap / priority queue, which is mutable, of size k+1.
    val pq = PriorityQueue<T>(minVal)

    for (i in 0 until minVal) {
        pq.add(list[0])
        list.removeAt(0)
    }

    while (list.isNotEmpty()) {
        // We add to the front of sortedList since it is a linked list, and thus only takes O(1).
        // We will reverse it at the end.
        sortedList.add(0, pq.poll())
        pq.add(list[0])
        list.removeAt(0)
    }

    while (pq.isNotEmpty())
        sortedList.add(0, pq.poll())

    return sortedList.filterNotNull().reversed()
}
