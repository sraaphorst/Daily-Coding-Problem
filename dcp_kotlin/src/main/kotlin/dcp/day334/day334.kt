package dcp.day334
// day334.kt
// By Sebastian Raaphorst, 2020.

private const val length: Int = 4
private const val goal: Int = 24

// Backtrack over nums, determine if the number 24 can be made from combining them in order with +, *, -, /, and
// parentheses.
fun play24(nums: List<Int>): Boolean {
    require(nums.size == length)

    // Backtracking, trying eack operation.
    tailrec
    fun aux(idx: Int = 0, scores: Set<Int> = nums.toSet()): Boolean {
        // At this point, all possible scores that can be made from nums have been made.
        // Determine if the goal, 24, is amongst them.
        if (idx == length - 1)
            return goal in scores

        // We must use elem in the next computation.
        val elem = nums[idx]
        val newScores = scores.flatMap{s -> setOf(s + elem, s - elem, s * elem, s / elem)}.toSet()
        return aux(idx + 1, newScores)
    }

    return aux()
}
