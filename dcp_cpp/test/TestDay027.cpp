/**
 * TestDay027.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>
#include <iterator>
#include <set>
#include <vector>

#include "day027/day027.h"

using namespace dcp::day027;

TEST_CASE("Day027: Empty string should return true") {
    REQUIRE(is_well_formed(""));
}

TEST_CASE("Day027: ([])[]({}) should return true") {
    REQUIRE(is_well_formed("([])[]({})"));
}

TEST_CASE("Day027: ([)] should return false") {
    REQUIRE(!is_well_formed("([)]"));
}

TEST_CASE("Day027: ((() should return false") {
    REQUIRE(!is_well_formed("((()"));
}
