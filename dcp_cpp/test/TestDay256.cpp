/**
 * TestDay256.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <list>

#include <day256/day256.h>
using namespace dcp::day256;

TEST_CASE("Day 256: alternating integers, odd") {
    std::list<int> lst{1, 2, 3, 4, 5, 6, 7};
    alternate(lst.begin(), lst.end());
    REQUIRE(lst == std::list<int>{1, 3, 2, 5, 4, 7, 6});
}

TEST_CASE("Day 256: alternating integers, even") {
    std::list<int> lst{1, 2, 3, 4, 5, 6, 7, 8};
    alternate(lst.begin(), lst.end());
    REQUIRE(lst == std::list<int>{1, 3, 2, 5, 4, 7, 6, 8});
}