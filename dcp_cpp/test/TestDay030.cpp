/**
 * TestDay030.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>

#include "day030/day030.h"

using namespace dcp::day030;

TEST_CASE("Day030: Empty case") {
    constexpr std::array<size_t, 0> elevations{};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 0);
}

TEST_CASE("Day030: One wall") {
    constexpr std::array<size_t, 1> elevations{1};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 0);
}

TEST_CASE("Day030: Two walls") {
    constexpr std::array<size_t, 2> elevations{1, 2};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 0);
}

TEST_CASE("Day030: Tiny left valley") {
    constexpr std::array<size_t, 3> elevations{3, 0, 2};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 2);
}

TEST_CASE("Day030: Tiny right valley") {
    constexpr std::array<size_t, 3> elevations{2, 0, 3};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 2);
}

TEST_CASE("Day030: Plateau") {
    constexpr std::array<size_t, 5> elevations{3, 3, 3, 3, 3};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 0);
}

TEST_CASE("Day030: Right staircase") {
    constexpr std::array<size_t, 5> elevations{5, 4, 3, 2, 1};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 0);
}

TEST_CASE("Day030: Left staircase") {
    constexpr std::array<size_t, 5> elevations{1, 2, 3, 4, 5};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 0);
}


TEST_CASE("Day030: Example in header file") {
    constexpr std::array<size_t, 16> elevations{1, 2, 4, 3, 1, 3, 2, 2, 6, 3, 4, 2, 3, 1, 1, 2};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 13);
}

TEST_CASE("Day030: Given test case 1") {
    std::array<size_t, 3> elevations{2, 1, 2};
    auto water = captured_water(elevations);
    REQUIRE(water == 1);
}

TEST_CASE("Day030: Given test case 2") {
    constexpr std::array<size_t, 6> elevations{3, 0, 1, 3, 0, 5};
    constexpr auto water = captured_water(elevations);
    REQUIRE(water == 8);
}
