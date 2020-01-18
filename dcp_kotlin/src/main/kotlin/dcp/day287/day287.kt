package dcp.day287
// day287.kt
// By Sebastian Raaphorst, 2020.

import java.util.PriorityQueue

typealias UserId = Int
typealias Watcher<T> = Pair<T, UserId>
typealias WatcherInfoList<T> = List<Pair<T, Set<UserId>>>
typealias Similarity<T> = Pair<T, T>

/**
 * The similarity score between two Ts (assumed to be TV shows, but could be anything with observers).
 */
data class SimilarityScore<T>(val score: Double, val show1: T, val show2: T)

/**
 * A comparator for SimilarityScore to be used in the priority queue.
 */
class SimilarityScoreComparator<T> : Comparator<SimilarityScore<T>> {
    override fun compare(o1: SimilarityScore<T>?, o2: SimilarityScore<T>?): Int {
        require(o1 != null)
        require(o2 != null)
        val diff = o1.score - o2.score
        return when {
            diff < 0.0 -> -1
            diff > 0.0 -> 1
            diff == 0.0 -> 0
            else -> 0
        }
    }
}

fun <T> calculateNMostSimilarShows(n: Int, watcherData: Collection<Watcher<T>>): List<Similarity<T>> {
    /**
     * We will use the Jaccard index to determine the similarity between two programs.
     * Convert the programs from pairs (progId, userID) to sets of userID.
     * Then for prog A and for prog B, we determine their similarity using the Jaccard
     * index:
     *
     *    J(A, B) = |A symm diff B| / |A union B|
     *
     * We need to avoid division by 0: in that case, just return 0.
     */
    fun jaccardIndex(s1: Set<Int>, s2: Set<Int>): Double {
        val union = s1.union(s2)
        return if (union.isEmpty()) 0.0
        else (union - s1.intersect(s2)).size.toDouble() / union.size.toDouble()
    }

    /**
     * Create a priority queue of similarity scores.
     * In Java, a priority queue is based on a max heap, so they have the same performance.
     */
    fun calculateSimilarPrograms(): PriorityQueue<SimilarityScore<T>> {
        // Group together the watcher data into sets.
        val watcherInfoList: WatcherInfoList<T> = watcherData.groupBy { it.first }
            .mapValues { (_, v) -> v.map { it.second }.toSet() }.toList()
        val size = watcherInfoList.size

        // The similarity relationship is reflexive.
        val pq = PriorityQueue<SimilarityScore<T>>(size * (size - 1), SimilarityScoreComparator())
        for (i in 0 until size - 1)
            for (j in i + 1 until size)
                pq.add(
                    SimilarityScore(
                        jaccardIndex(watcherInfoList[i].second, watcherInfoList[j].second),
                        watcherInfoList[i].first,
                        watcherInfoList[j].first
                    )
                )
        return pq
    }

    /**
     *  Then we can take the top k to represent the k most similar programs, dropping score.
     */
    return calculateSimilarPrograms().take(n).map { Pair(it.show1, it.show2) }
}
