/**
 * TestDay304.cpp
 *
 * By Sebastian Raaphorst, 2020.
 */

#include <catch.hpp>
#include <cmath>

#include <day304/day304.h>
using namespace dcp::day304;

TEST_CASE("Day 304: Make sure all positions in [0,3]x[0,3] match for five steps") {
    for (size_t x = 0; x < 3; ++x)
        for (size_t y = 0; y < 3; ++y)
            for (size_t s = 1; s < 6; ++ s)
                REQUIRE(fabs(knight_survival_probability_dp(x, y, s) - knight_survival_probability_markov(x, y, s)) < 1e-6);
}

TEST_CASE("Day 304: Make sure both functions are constexpr") {
    constexpr auto dp = knight_survival_probability_dp(0, 0, 3);
    constexpr auto mk = knight_survival_probability_markov(0, 0, 3);
    REQUIRE(fabs(dp - mk) < 1e-6);
}