package dcp.day244

val sieve = sequence {
    val primes = mutableListOf<Int>()
    var curr = 2
    while (true) {
        if (primes.none { curr % it == 0 }) {
            primes.add(curr)
            yield(curr)
        }
        curr += 1
    }
}
