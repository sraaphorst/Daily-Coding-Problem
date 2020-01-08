package dcp.day275
// day275.kt
// By Sebastian Raaphorst, 2020.

// Generate the look-and-say sequence using coroutines to continue and tail recursion to generate one term from
// the next.
fun lookAndSay() = sequence {
    // Given a term, generate the next using tail recursion.
    tailrec
    fun aux(prev: List<Char>, next: String = ""): String {
        if (prev.isEmpty())
            return next
        val first = prev.first()
        val count = prev.takeWhile { it == first }.size
        val rest = prev.drop(count)
        return aux(rest, next + count.toString() + first.toString())
    }
    var term = "1"
    while (true) {
        yield(term)
        term = aux(term.toList())
    }
}

fun main() {
    lookAndSay().take(10).forEach{println(it)}
}