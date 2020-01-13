/**
 * day268.h
 *
 * By Sebastian Raaphorst, 2019.
 */


#include <catch.hpp>

#include <day214/day214.h>
using namespace dcp::day214;

TEST_CASE("Day 214: no 1s") {
    constexpr auto count = countConsecutive1s(0);
    REQUIRE(count == 0);
}

TEST_CASE("Day 214: one 1") {
    constexpr auto count = countConsecutive1s(0b00010000);
    REQUIRE(count == 1);
}

TEST_CASE("Day 214: one 2") {
    constexpr auto count = countConsecutive1s(0b10101011010);
    REQUIRE(count == 2);
}

TEST_CASE("Day 214: one 3") {
    constexpr auto count = countConsecutive1s(0b10110101101110011);
    REQUIRE(count == 3);
}

TEST_CASE("Day 214: one 4") {
    constexpr auto count = countConsecutive1s(0b11101011011101111);
    REQUIRE(count == 4);
}