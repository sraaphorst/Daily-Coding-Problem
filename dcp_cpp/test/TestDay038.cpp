/**
 * TestDay038.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>
#include <iostream>
#include <tuple>

#include "day038/day038.h"

using namespace dcp::day038;

TEST_CASE("Day039: Proper results, generated constexpr.") {
    constexpr auto sol0 = NQueens<0>::calculate();
    REQUIRE(sol0 == 0);
    constexpr auto sol1 = NQueens<1>::calculate();
    REQUIRE(sol1 == 1);
    constexpr auto sol2 = NQueens<2>::calculate();
    REQUIRE(sol2 == 0);
    constexpr auto sol3 = NQueens<3>::calculate();
    REQUIRE(sol3 == 0);
    constexpr auto sol4 = NQueens<4>::calculate();
    REQUIRE(sol4 == 2);
    constexpr auto sol5 = NQueens<5>::calculate();
    REQUIRE(sol5 == 10);
    constexpr auto sol6 = NQueens<6>::calculate();
    REQUIRE(sol6 == 4);
    constexpr auto sol7 = NQueens<7>::calculate();
    REQUIRE(sol7 == 40);

}