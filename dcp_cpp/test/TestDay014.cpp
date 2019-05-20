/**
 * TestDay014.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <math.h>

#include "day014/day014.h"

using namespace dcp::day014;

TEST_CASE("Day014: Monte Carlo pi estimation") {
    const auto pi = estimate_pi();
    REQUIRE(std::abs(pi - M_PI) < 1e-3);
}