/**
 * TestDay118.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>

#include <day118/day118.h>
using namespace dcp::day118;

TEST_CASE("Square and sort an array in time and space O(n) using constexpr") {
    constexpr std::array<int, 10> arr{{-9, -7, -3, -1, 0, 1, 6, 7, 8, 10}};
    constexpr auto n_length = neg_element_length(arr);
    constexpr auto p_length = arr.size() - n_length;
    constexpr auto arr_neg = reverse(square(first_elements<n_length>(arr)));
    constexpr auto arr_pos = square(last_elements<p_length>(arr));
    constexpr auto arr_combined = merge(arr_neg, arr_pos);

    constexpr std::array<int, 10> result{{0, 1, 1, 9, 36, 49, 49, 64, 81, 100}};
    REQUIRE(arr_combined == result);
}