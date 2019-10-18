/**
 * TestDay157.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <day157/day157.h>
using namespace dcp::day157;

TEST_CASE("Palindrome empty string") {
    REQUIRE(is_palindrome_permutation(""));
}

TEST_CASE("Palindromes with even-only characters") {
    REQUIRE(is_palindrome_permutation("noon"));
    REQUIRE(is_palindrome_permutation("nono"));
    REQUIRE(is_palindrome_permutation("neveroddoreven"));
    REQUIRE(is_palindrome_permutation("neverevenorodd"));
}

TEST_CASE("Palindromes with odd characters") {
    REQUIRE(is_palindrome_permutation("racecar"));
    REQUIRE(is_palindrome_permutation("carrace"));
    REQUIRE(is_palindrome_permutation("tacocat"));
    REQUIRE(is_palindrome_permutation("cattaco"));
    REQUIRE(is_palindrome_permutation("madamimadam"));
    REQUIRE(is_palindrome_permutation("imadammadam"));
    REQUIRE(is_palindrome_permutation("amanaplanacanalpanama"));
    REQUIRE(is_palindrome_permutation("panamaacanalaplanaman"));
}

TEST_CASE("Non-palindromes") {
    REQUIRE(!is_palindrome_permutation("daily"));
    REQUIRE(!is_palindrome_permutation("thiscatisataco"));
    REQUIRE(!is_palindrome_permutation("momoom"));
}