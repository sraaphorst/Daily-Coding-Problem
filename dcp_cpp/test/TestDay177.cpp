/**
 * TestDay177.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <list>

#include <day177/day177.h>
using namespace dcp::day177;

TEST_CASE("Day177: 7 -> 7 -> 3 -> 5 by 2") {
    std::list<int> lst{7, 7, 3, 5};
    REQUIRE(rotate_right(2, lst) == std::list<int>{3, 5, 7, 7});
}

TEST_CASE("Day177: 1 -> 2 -> 3 -> 4 -> 5 by 3") {
    std::list<int> lst{1, 2, 3, 4, 5};
    REQUIRE(rotate_right(3, lst) == std::list<int>{3, 4, 5, 1, 2});
}