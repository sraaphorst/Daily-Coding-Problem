/**
 * TestDay109.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <day109/day109.h>
using namespace dcp::day109;

TEST_CASE("First provided test case") {
    REQUIRE(swap_bits(0b10101010) == 0b01010101);
}

TEST_CASE("First provided test case, brute force") {
    REQUIRE(swap_bits_brute_force(0b10101010) == 0b01010101);
}

TEST_CASE("Second provided test case") {
    REQUIRE(swap_bits(0b11100010) == 0b11010001);
}

TEST_CASE("Second provided test case, brute force") {
    REQUIRE(swap_bits_brute_force(0b11100010) == 0b11010001);
}

TEST_CASE("Try all 128 values, which should match brute force and be self-inverting") {
    for (uint8_t val = 0; val < 0xff; ++val) {
        REQUIRE(swap_bits(val) == swap_bits_brute_force(val));
        REQUIRE(swap_bits(swap_bits(val)) == val);
        REQUIRE(swap_bits_brute_force(swap_bits_brute_force(val)) == val);
    }
}
