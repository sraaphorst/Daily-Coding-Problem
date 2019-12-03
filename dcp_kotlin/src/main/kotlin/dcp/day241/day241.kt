package dcp.day241
// day241.kt
// By Sebastian Raaphorst, 2019.

fun calculateHIndex(paperCitations: List<Int>): Int? =
    (0..(paperCitations.size)).filter{ citations -> paperCitations.count { it >= citations } >= citations}.max()
