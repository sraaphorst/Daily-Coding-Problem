/**
 * TestDay321.cpp
 *
 * By Sebastian Raaphorst, 2020.
 */

#include <catch.hpp>
#include <vector>

#include <day321/day321.h>
using namespace dcp::day321;

TEST_CASE("Day 321: Make sure matches Kotlin implementation.") {
    const std::vector<unsigned long long> expect{2, 3, 5, 7, 11, 22, 23, 173, 347, 1319, 4198, 85643};
    std::vector<unsigned long long> calc{};

    T maxVal = 0ULL;
    for (T it = 1L; it < 100000; ++it) {
        const auto value = stepToOne(it);
        if (value > maxVal) {
            maxVal = value;
            calc.push_back(it);
        }
    }
    REQUIRE(calc == expect);
}
