/**
 * TestDay180.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <numeric>
#include <random>

#include <day184/day184.h>
using namespace dcp::day184;

TEST_CASE("Day 184: Specific constexpr case") {
    // Can be used as constexpr.
    constexpr auto h = gcd(42, 56, 14);
    REQUIRE(h == 14);
}

TEST_CASE("Day 184: Random four number GCD") {
    std::random_device rd;
    std::mt19937 gen{rd()};
    std::uniform_int_distribution<> dis(-1'000'000, 1'000'000);

    for (size_t i = 0; i < 1'000'000; ++i) {
        const auto a = dis(gen);
        const auto b = dis(gen);
        const auto c = dis(gen);
        const auto d = dis(gen);
        REQUIRE(gcd(a, b, c, d) == std::gcd(a, std::gcd(b, std::gcd(c, d))));
    }
}