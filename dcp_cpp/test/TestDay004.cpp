/**
 * TestDay004.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>
#include <iterator>

#include <day004/day004.h>

using namespace dcp::day004;

TEST_CASE("Day004: Test [3, 4, -1, 1") {
    std::array<int, 4> arr{{3, 4, -1, 1}};
    REQUIRE(find_lowest_missing_positive(std::begin(arr), std::end(arr)) == 2);
}
