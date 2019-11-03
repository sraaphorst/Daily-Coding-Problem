/**
 * TestDay208.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <day208/day208.h>
using namespace dcp::day208;
//
constexpr auto N = 100000;

// There really is no way to test the conjecture that the Collatz sequence ends for any value: if may not, and
// recurse infinitely (the Halting problem). We just run through the range of values and show that it ends.
TEST_CASE("Day 208: Collatz sequence ends.") {
    auto seq = populate_collatz<N>();
    REQUIRE(seq.size() == N);
}

TEST_CASE("Day 208: Longest Collatz sequence") {
    REQUIRE(collatz(77031) == 350);
}