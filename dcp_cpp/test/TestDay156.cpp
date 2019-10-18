/**
 * TestDay156.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <day156/day156.h>
using namespace dcp::day156;

TEST_CASE("Various test cases for brute force") {
    REQUIRE(brute_force(0) == 0);
    REQUIRE(brute_force(1) == 1);
    REQUIRE(brute_force(2) == 2);
    REQUIRE(brute_force(3) == 3);
    REQUIRE(brute_force(4) == 1);
    REQUIRE(brute_force(5) == 2);
    REQUIRE(brute_force(6) == 3);
    REQUIRE(brute_force(7) == 4);
    REQUIRE(brute_force(8) == 2);
    REQUIRE(brute_force(9) == 1);
    REQUIRE(brute_force(10) == 2);
    REQUIRE(brute_force(11) == 3);
    REQUIRE(brute_force(12) == 3);
    REQUIRE(brute_force(14) == 3);
    REQUIRE(brute_force(15) == 4);
    REQUIRE(brute_force(16) == 1);
    REQUIRE(brute_force(17) == 2);
    REQUIRE(brute_force(18) == 2);
    REQUIRE(brute_force(19) == 3);
    REQUIRE(brute_force(20) == 2);
    REQUIRE(brute_force(21) == 3);
    REQUIRE(brute_force(22) == 3);
    REQUIRE(brute_force(23) == 4);
    REQUIRE(brute_force(24) == 3);
    REQUIRE(brute_force(25) == 1);
}

TEST_CASE("Various test cases for Legendre theorem use") {
    for (auto i = 0; i <= 60; ++i)
        REQUIRE(brute_force(i) == legendre_num_squared_integers(i));
}

TEST_CASE("Large case: shows speed of Legendre vs. brute force") {
    REQUIRE(legendre_num_squared_integers(2'000'000) == 2);
}