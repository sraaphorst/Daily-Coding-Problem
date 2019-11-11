package dcp.day202
// dcp.day202.kt
// By Sebastian Raaphorst, 2019

// Reverse the number and compare.
// If we compare numbers from left and right and remove them, we get into trouble when we run into numbers like 1011,
// where removing a number from the left implicitly removes two from the left due to the 0.

fun Int.isPalindrome(): Boolean {
    // Negative numbers are not palindromes.
    if (this < 0)
        return false

    var num = this
    var reversedNumber = 0
    while (num > 0) {
        reversedNumber = reversedNumber * 10 + num % 10
        num /= 10
    }

    return this == reversedNumber
}
