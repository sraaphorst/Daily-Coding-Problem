/**
 * TestDay155.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <iterator>

#include <day155/day155.h>
using namespace dcp::day155;

TEST_CASE("Provided test case") {
    constexpr std::array<int, 7> arr{{1, 2, 1, 1, 3, 4, 0}};
    auto answer = find_majority_element(std::cbegin(arr), std::cend(arr));
    REQUIRE((answer.has_value() and answer.value() == 1));
}
