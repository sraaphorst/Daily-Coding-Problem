package dcp.day298
// day298.kt
// By Sebastian Raaphorst, 2020.

import kotlin.math.max

typealias AppleType = Int
typealias ApplePath = List<AppleType>
/**
 * Apple picking: this corresponds to the longest subarray that consists of just two values.
 */
fun applePicking(path: ApplePath): Int {
    if (path.size <= 2)
        path.size

    require(path.all { it >= 0 })
    /**
     * We maintain:
     * 1. The remaining path.
     * 2. The apple we saw two tree types ago.
     * 3. The apple we saw on the last tree type.
     * 4. The number of apples we saw of the last tree type.
     *      This is because, say, we meet ABABBBC, when we start at C, we will start a new segment with type1 = B,
     *      type2 = C, and totalCount = 3B + 1C = 4.
     * 5. The total segment length we are currently on.
     * 6. The maximum segment length we have seen so far.
     */
    tailrec
    fun aux(remainingPath: ApplePath = path,
            prevType1: AppleType = -1,
            prevType2: AppleType = 0,
            prevType2Count: Int = 0,
            totalCount: Int = 0,
            maxSoFar: Int = 0): Int {
        if (remainingPath.isEmpty())
            return max(totalCount, maxSoFar)

        // Go down the path taking all of the apples of the same type.
        val newType = remainingPath.first()
        val newTypeCount = remainingPath.takeWhile { it == newType }.count()

        // If we have switched types of apples, calculate the next max and reset.
        if (newType != prevType1)
            return aux(remainingPath.drop(newTypeCount), prevType2, newType, newTypeCount, prevType2Count + newTypeCount, max(totalCount, maxSoFar))

        // If we don't switch types of apples, extend.
        return aux(remainingPath.drop(newTypeCount), prevType2, newType, newTypeCount, totalCount + newTypeCount, maxSoFar)
    }

    return aux()
}
