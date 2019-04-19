/**
 * TestDay1.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>
#include <iterator>

#include "day001/day001.h"

using namespace dcp::day001;

TEST_CASE("Day001: Testing empty list") {
    constexpr std::array<int, 0> arr{};
    REQUIRE(!contains_sum(0, std::cbegin(arr), std::cend(arr)));
}

TEST_CASE("Day001: Testing example list containing sum") {
    constexpr std::array<int, 4> arr{10, 15, 3, 7};
    REQUIRE(contains_sum(17, std::cbegin(arr), std::cend(arr)));
}

TEST_CASE("Day001: Testing example list not containing sum") {
    constexpr std::array<int, 4> arr{10, 15, 3, 7};
    REQUIRE(!contains_sum(14, std::cbegin(arr), std::cend(arr)));
}

TEST_CASE("Day001: Testing example list to make sure element cannot be added twice") {
    constexpr std::array<int, 4> arr{10, 15, 3, 7};
    REQUIRE(!contains_sum(20, std::cbegin(arr), std::cend(arr)));
}
