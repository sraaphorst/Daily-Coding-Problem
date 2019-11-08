package dcp.day215
// day215.kt
// By Sebastian Raaphorst, 2019.

private typealias Depth = Int
private typealias HorizontalDistance = Int
private typealias HorizonalDistanceInformation<T> = Map<HorizontalDistance, Map<Depth, T>>

// The Ts are just to describe the bottom layer. In the example, they are Int.
class HDNode<T>(
    val value: T,
    val left: HDNode<T>? = null,
    val right: HDNode<T>? = null) {
    fun isLeaf(): Boolean {
        return left == null && right == null
    }
    fun isNode(): Boolean {
        return !isLeaf()
    }

    fun findBottomView(): List<T> {
        fun aux(cur: HDNode<T>,
                horizontalDistance: HorizontalDistance,
                depth: Int,
                info: HorizonalDistanceInformation<T>): HorizonalDistanceInformation<T> {
            // If there is no value at this horizontal distance, we use this one.
            if (info[horizontalDistance] == null) {
                info[horizontalDistance]?.plus(mapOf(depth to value))
                val xyz = info[horizontalDistance].apply { depth to value }
                info.let { depth to value }
            }
            return mapOf()
        }
        return listOf()
    }
}
//
//private operator fun <A, B> Pair<A, B>?.get(depth: A) {
//
//}
