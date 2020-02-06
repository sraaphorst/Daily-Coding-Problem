package dcp.day305
// day305.kt
// By Sebastian Raaphorst, 2020.

/**
 * Lists in Kotlin are linked lists, so this should meet the requirements.
 */
fun List<Int>.removeZeroSumSublists(): List<Int> {
    fun aux(start: Int = 0, end: Int = 0, sum: Int = 0, remainingList: List<Int> = filter { it != 0 }): List<Int> = when {
        // We have found a nonempty sublist summing to 0: remove it.
        sum == 0 && start != end ->
            aux(0, 0, 0, remainingList.slice(0 until start) + remainingList.slice(end until (remainingList.size)))

        // We have eliminated the entire list or have processed it all: the second condition covers the first but we
        // include them both for clarity.
        remainingList.isEmpty() || start >= remainingList.size ->
            remainingList

        // We have reached the end of the list starting at position start and have not found a nonempty sublist summing
        // to 0, so increase start and try again.
        end == remainingList.size ->
            aux(start + 1, start + 1, 0, remainingList)

        // We are processing the list from start and continue to extend.
        else ->
            aux(start, end + 1, sum + remainingList[end], remainingList)
    }

    return aux()
}

fun main() {
    val lst = listOf(3, 4, 0, -7, 0, 5, 0, -6, 6, 0)
    println(lst.removeZeroSumSublists())
}