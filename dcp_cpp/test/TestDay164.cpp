/**
 * TestDay164.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <array>
#include <numeric>
#include <string>

#include <day164/day164.h>
using namespace dcp::day164;

TEST_CASE("First 10 intengers") {
    std::array<int, 10> arr{{0, 1, 2, 3, 4, 5, 6, 7, 8, 8}};
    const auto result = apply_pidgeonhole<int>(std::cbegin(arr), std::cend(arr));
    REQUIRE(result.has_value());
    REQUIRE(result.value() == 8);
}

TEST_CASE("Strings") {
    std::array<std::string, 5> arr{{"hello", "world", "hello", "moon", "stars"}};
    const auto result = apply_pidgeonhole<std::string>(std::cbegin(arr), std::cend(arr));
    REQUIRE(result.has_value());
    REQUIRE(result.value() == "hello");
}

TEST_CASE("No pigeon hole principle violated") {
    std::array<std::size_t, 5> arr{{0, 1, 2, 3, 4}};
    const auto result = apply_pidgeonhole<size_t>(std::cbegin(arr), std::cend(arr));
    REQUIRE(!result.has_value());
}

TEST_CASE("Double pigeonhole violation: find first") {
    std::array<std::size_t, 7> arr{{0, 1, 2, 1, 3, 4, 3}};
    const auto result = apply_pidgeonhole<size_t>(std::cbegin(arr), std::cend(arr));
    REQUIRE(result.has_value());
    REQUIRE(result.value() == 1);
}