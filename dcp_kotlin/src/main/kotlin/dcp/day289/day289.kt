package dcp.day289
// day289.kt
// By Sebastian Raaphorst, 2020.

// We can do this two ways:
// The first way is the more complicated minimax way, which entails calculating all moves and determining if the
// winning move is in the moves. If it is, it is possible for the first player to win Misere Nim.

fun nimMiniMax(heapList: List<Int>): Boolean {
    if (heapList.all { it == 0 })
        return false

    require(heapList.all { it >= 0 })

    // Memoize results.
    val results: MutableMap<List<Int>, Int> = mutableMapOf()

    fun getMoves(heaps: List<Int>): List<List<Int>> =
        heaps.withIndex().flatMap { (heapIdx, heapHeight) ->
            (1..heapHeight).map { remove -> heaps.withIndex().map { (idx2, h) -> if (heapIdx == idx2) h - remove else h } } }

    fun aux(heaps: List<Int> = heapList): Int {
        results[heaps]?.let { return it }

        if (heaps.all { it ==  0 })
            return 1

        val moves = getMoves(heaps)
        val result = moves.map { 1 - aux(it) }.max() ?: 0
        results[heaps] = result
        return result
    }

    return aux() == 1
}

// The second method is to use the "nim-sum" method, which is taking the xor of the heaps.
// The goal is to make moves to put the num-sum back to zero, which will put the opponent
// in the losing position.
fun nimSum(heapList: List<Int>): Boolean {
    if (heapList.all { it == 0 })
        return false

    require(heapList.all { it >= 0 })

    // Special case: odd number of heaps of size 1. Players alternate taking an entire heap, and the first player
    // is forced to take the last heap.
    if (heapList.size % 2 == 1 && heapList.all { it == 1 })
        return false

    return heapList.fold(0){acc, heap -> acc.xor(heap)} != 0
}
