/**
 * TestDay056.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>
#include <iostream>

#include <day056/day056.h>
using namespace dcp::day056;

// The Petersen graph G, chi(G) = 3.
constexpr adjacency_matrix<10> petersen{{
    {false, true, false, false, true, true, false, false, false, false}, // v1 outer
    {true, false, true, false, false, false, true, false, false, false}, // v2 outer
    {false, true, false, true, false, false, false, true, false, false}, // v3 outer
    {false, false, true, false, true, false, false, false, true, false}, // v4 outer
    {true, false, false, true, false, false, false, false, false, true}, // v5 outer
    {true, false, false, false, false, false, false, true, true, false}, // v6 inner
    {false, true, false, false, false, false, false, false, true, true}, // v7 inner
    {false, false, true, false, false, true, false, false, false, true}, // v8 inner
    {false, false, false, true, false, true, true, false, false, false}, // v9 inner
    {false, false, false, false, true, false, true, true, false, false} // v10 inner
}};

TEST_CASE("The Petersen graph can be 3-coloured") {
    // This is a deterministic algorithm, and this is the colouring (which is valid) that emerges.
    constexpr auto colouring = colour<10,3>(petersen);
    constexpr std::array<int, 10> valid_colouring{{0, 1, 0, 1, 2, 1, 0, 2, 2, 1}};
    REQUIRE(colouring == valid_colouring);
}

TEST_CASE("The Petersen graph cannot be 2-coloured") {
    constexpr auto colouring = colour<10,2>(petersen);
    constexpr std::array<int, 10> fail{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    REQUIRE(colouring == fail);
}
