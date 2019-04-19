/**
 * TestDay002.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>
#include <iterator>

#include "day002/day002.h"

using namespace dcp::day002;

TEST_CASE("Day002: List [1, 2, 3, 4, 5]") {
    constexpr std::array<int, 5> arr{{1, 2, 3, 4, 5}};
    std::array<int, 5> result{};
    constexpr std::array<int, 5> expected{{120, 60, 40, 30, 24}};

    calculate_products(std::cbegin(arr), std::cend(arr), std::begin(result));

    for (size_t i = 0; i < 5; ++i)
        REQUIRE(result[i] == expected[i]);
}
