/**
 * TestDay248.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <cmath>
#include <random>

#include <day248/day248.h>

TEST_CASE("Day 248: random samples of signed integers") {
    std::random_device rd;
    std::mt19937 gen{rd()};
    std::uniform_int_distribution<int32_t> dis{INT32_MIN/2, INT32_MAX/2};
    for (int i = 0; i < 1'000'000; ++i) {
        auto a = dis(gen);
        auto b = dis(gen);
        REQUIRE(dcp::day248::max(a, b) == dcp::day248::max(b, a));
        REQUIRE(dcp::day248::max(a, b) == std::max(a, b));
    }
}

TEST_CASE("Day 248: random samples of unsigned integers") {
    std::random_device rd;
    std::mt19937 gen{rd()};
    std::uniform_int_distribution<uint32_t> dis{0, UINT32_MAX/2};
    for (int i = 0; i < 1'000'000; ++i) {
        auto a = dis(gen);
        auto b = dis(gen);
        REQUIRE(dcp::day248::max(a, b) == dcp::day248::max(b, a));
        REQUIRE(dcp::day248::max(a, b) == std::max(a, b));
    }
}