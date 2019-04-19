/**
 * TestDay012.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>
#include <iterator>
#include <set>
#include <vector>

#include "day012/day012.h"

using namespace dcp::day012;

TEST_CASE("Day012: Testing with steps of size 1") {
    constexpr std::array<unsigned long, 1> arr{{1}};
    REQUIRE(unique_steps(10UL, std::cbegin(arr), std::cend(arr)) == 1UL);
}

TEST_CASE("Day012: Goal of zero should always have one way to reach it") {
    constexpr std::array<unsigned long, 2> arr{{1, 2}};
    REQUIRE(unique_steps(0UL, std::cbegin(arr), std::cend(arr)) == 1UL);
}

TEST_CASE("Day012: Testing one fibonacci") {
    constexpr std::array<unsigned long, 2> arr{{1, 2}};
    REQUIRE(unique_steps(4UL, std::cbegin(arr), std::cend(arr)) == 5);
}


TEST_CASE("Day012: Testing multiple fibonacci") {
    constexpr std::array<unsigned long, 2> arr{{1, 2}};
    constexpr std::array<unsigned long, 10> fib{{1, 1, 2, 3, 5, 8, 13, 21, 34, 55}};

    for (unsigned long i = 0; i < 10; ++i)
        REQUIRE(unique_steps(i, std::cbegin(arr), std::cend(arr)) == fib[i]);
}

TEST_CASE("Day012: Testing vector") {
    std::vector<unsigned long> arr{1, 2};
    constexpr std::array<unsigned long, 10> fib{{1, 1, 2, 3, 5, 8, 13, 21, 34, 55}};
    for (unsigned long i = 0; i < 10; ++i)
        REQUIRE(unique_steps(i, std::cbegin(arr), std::cend(arr)) == fib[i]);
}

TEST_CASE("Day012: Testing set") {
    std::set<unsigned long> arr{1, 2};
    constexpr std::array<unsigned long, 10> fib{{1, 1, 2, 3, 5, 8, 13, 21, 34, 55}};
    for (unsigned long i = 0; i < 10; ++i)
        REQUIRE(unique_steps(i, std::cbegin(arr), std::cend(arr)) == fib[i]);
}

TEST_CASE("Day012: Testing {1, 3, 5}") {
    std::set<unsigned long> arr{1, 3, 5};
    constexpr std::array<unsigned long, 20> seq{{1, 1, 1, 2, 3, 5, 8, 12, 19, 30, 47, 74, 116, 182, 286, 449, 705,
                                                 1107, 1738, 2729}};
    for (unsigned long i = 0; i < 20; ++i)
        REQUIRE(unique_steps(i, std::cbegin(arr), std::cend(arr)) == seq[i]);
}
