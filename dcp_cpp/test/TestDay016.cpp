/**
 * TestDay016.cpp
 *
 * By Sebastian Raaphorst, 2020.
 */

#include <catch.hpp>
#include <array>
#include <stdexcept>

#include "day016/day016.h"

using namespace dcp::day016;

constexpr size_t size = 5;

TEST_CASE("Day016: Test order numbers initialized to 0") {
    OrderHistory<size> history{};
    for (size_t i = 0; i < size; ++i)
        REQUIRE(history.getLast(i) == 0);
}

TEST_CASE("Day016: Test filled history retrieves properly") {
    OrderHistory<size> history{};
    for (LogID i = 0; i < size; ++i)
        history.record(i + 1);
    for (size_t i = 0; i < size; ++i)
        REQUIRE(history.getLast(i) == size - i);
}

TEST_CASE("Day016: Test filled history overwrites properly") {
    OrderHistory<size> history{};
    std::array<LogID, 5> expected{{8, 7, 6, 5, 4}};
    for (size_t i = 0; i <= 8; ++i)
        history.record(i);
    for (size_t i = 0; i < size; ++i)
        REQUIRE(history.getLast(i) == expected[i]);
}

TEST_CASE("Day016: Exception for illegal index") {
    OrderHistory<size> history{};
    REQUIRE_THROWS_AS(history.getLast(-1), std::invalid_argument);
    REQUIRE_THROWS_AS(history.getLast(size+1), std::invalid_argument);
}