/**
 * TestDay161.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <day161/day161.h>
using namespace dcp::day161;

TEST_CASE("Flip 1111 0000 1111 0000 1111 0000 1111 0000") {
    constexpr uint32_t orig = 0b11110000111100001111000011110000;
    constexpr uint32_t res  = reverse_digits(orig);
    constexpr uint32_t exp  = 0b00001111000011110000111100001111;
    REQUIRE(res == exp);
}

TEST_CASE("Double flip") {
    constexpr uint32_t orig = 0xabcd;
    REQUIRE(reverse_digits((reverse_digits(orig))) == orig);
}

TEST_CASE("Full flips") {
    REQUIRE(reverse_digits(0) == 0xffffffff);
    REQUIRE(reverse_digits(0xffffffff) == 0);
}