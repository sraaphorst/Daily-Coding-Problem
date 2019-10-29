/**
 * TestDay064.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>
#include <iostream>

#include <day064/day064.cpp>
//using namespace dcp::day064;

// We only test non-isomorphic starting positions in a 5x5 board, which corresponds to to the OEIS:
// http://oeis.org/A165134
//TEST_CASE("Day64: Position (0,0) in an N=5 board") {
//    constexpr auto boards = dcp::day064::KnightsTour<5>::computeFrom(0, 0);
//    REQUIRE(boards == 304);
//}
//
//TEST_CASE("Day64: Position (0,1) in an N=5 board") {
//    constexpr auto boards = dcp::day064::KnightsTour<5>::computeFrom(0, 1);
//    REQUIRE(boards == 0);
//}
//
//TEST_CASE("Day64: Position (0,2) in an N=5 board") {
//    constexpr auto boards = dcp::day064::KnightsTour<5>::computeFrom(0, 2);
//    REQUIRE(boards == 56);
//}
//
//TEST_CASE("Day64: Position (1,1) in an N=5 board") {
//    constexpr auto boards = dcp::day064::KnightsTour<5>::computeFrom(1, 1);
//    REQUIRE(boards == 56;
//}
//
//TEST_CASE("Day64: Position (2,1) in an N=5 board") {
//    auto boards = dcp::day064::KnightsTour<5>::computeFrom(2, 1);
//    REQUIRE(boards == 0);
//}
//
//
//TEST_CASE("Day64: Position (2,2) in an N=5 board") {
//    constexpr auto boards = dcp::day064::KnightsTour<5>::computeFrom(2, 2);
//    REQUIRE(boards == 64);
//}
