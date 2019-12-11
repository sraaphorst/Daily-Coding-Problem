/**
 * TestDay006.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include "day006/day006.h"
using namespace dcp::day006;

TEST_CASE("Day 006: increasing list") {
    xor_list<int> lst{};

    for (int i = 0; i < 10; ++i) {
        lst.add(i);
        for (int j = 0; j <= i; ++j)
            REQUIRE(lst.get(j) == j);
    }
}