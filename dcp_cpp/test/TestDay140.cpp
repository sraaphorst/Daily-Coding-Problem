/**
 * TestDay140.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>

#include <day140/day140.h>
using namespace dcp::day140;

template<typename T>
constexpr bool pair_sets_equal(const std::pair<T, T> &s1, const std::pair<T, T> &s2) {
    return (s1.first == s2.first && s1.second == s2.second) || (s1.first == s2.second && s1.second == s2.first);
}

TEST_CASE("Identify two unique elements") {
    constexpr std::array<int, 8> array{{2, 4, 6, 8, 10, 2, 6, 8}};
    constexpr auto result = find_two_nonrepeating_elements(std::cbegin(array), std::cend(array));
    REQUIRE(pair_sets_equal(result, std::make_pair(4, 10)));
}

TEST_CASE("Identify two unique elements in small set") {
    constexpr std::array<int, 2> array{{0, 1}};
    constexpr auto result = find_two_nonrepeating_elements(std::cbegin(array), std::cend(array));
    REQUIRE(pair_sets_equal(result, std::make_pair(0, 1)));
}
