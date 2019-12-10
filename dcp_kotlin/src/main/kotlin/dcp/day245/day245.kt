package dcp.day245
// day245.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.min

fun brute_force(moves: List<Int>): Int? {
    if (moves.isEmpty())
        return 0

    fun aux(position: Int = 0, movesSoFar: Int = 0): Int? {
        // Determine the possible moves.
        if (position == moves.size - 1)
            return movesSoFar
        if (moves[position] == 0)
            return null

        // Determine the positions to which we can move.
        val maxPosition = position + moves[position]
        val newPositions = (position + 1)..min(moves.size - 1, maxPosition)
        val options = newPositions.map { aux(it, movesSoFar + 1) }
        return options.filterNotNull().min()
    }

    return aux()
}

fun dynamic_programming(moves: List<Int>): Int? {
    if (moves.isEmpty())
        return 0

    // dp[i] is the minimum number of moves needed to get to the end of the array from position i.
    // Set initially to values that are larger than the array.
    val N = moves.size
    val dp = Array(N){N + 1}
    dp[N - 1] = 0

    // Go backward, determining dp[i].
    (moves.size - 1 downTo 0).forEach { i ->
        // Furthest we can go from position.
        if (moves[i] != 0) {
            ((i + 1) until min(1 + i + moves[i], N)).forEach { j ->
                dp[i] = min(dp[i], 1 + dp[j])
            }
        }
    }
    return dp[0]
}
