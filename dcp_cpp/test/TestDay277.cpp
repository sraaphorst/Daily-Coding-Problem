/**
 * TestDay277.cpp
 *
 * By Sebastian Raaphorst, 2020.
 */

#include <catch.hpp>
#include <array>
#include <vector>

#include <day277/day277.h>
using namespace dcp::day277;

/**
 * Use constexpr to make sure our expressions are capable of returning constexpr.
 */

TEST_CASE("Day 277: count bits from left") {
    constexpr auto bits0 = details::count_bits_from_left(0b00000000u);
    REQUIRE(bits0 == 0);
    constexpr auto bits1 = details::count_bits_from_left(0b10000000u);
    REQUIRE(bits1 == 1);
    constexpr auto bits2 = details::count_bits_from_left(0b11000000u);
    REQUIRE(bits2 == 2);
    constexpr auto bits3 = details::count_bits_from_left(0b11100000u);
    REQUIRE(bits3 == 3);
    constexpr auto bits4 = details::count_bits_from_left(0b11110000u);
    REQUIRE(bits4 == 4);
    constexpr auto bits5 = details::count_bits_from_left(0b11111000u);
    REQUIRE(bits5 == 5);
    constexpr auto bits6 = details::count_bits_from_left(0b11111100u);
    REQUIRE(bits6 == 6);
    constexpr auto bits7 = details::count_bits_from_left(0b11111110u);
    REQUIRE(bits7 == 7);
    constexpr auto bits8 = details::count_bits_from_left(0b11111111u);
    REQUIRE(bits8 == 8);
}

TEST_CASE("Day 277: recognize a byte beginning with 10") {
    constexpr uint8_t byte = 0b10000000u;
    REQUIRE(details::starts_with_10(byte));
}

TEST_CASE("Day 277: recognize bytes not beginning with 10") {
    constexpr uint8_t byte1 = 0b00000000;
    REQUIRE_FALSE(details::starts_with_10(byte1));
    constexpr uint8_t byte2 = 0b01000000;
    REQUIRE_FALSE(details::starts_with_10(byte2));
    constexpr uint8_t byte3 = 0b11000000;
    REQUIRE_FALSE(details::starts_with_10(byte3));
}

TEST_CASE("Day 277: recognize a single byte unicode character") {
    constexpr std::array<uint8_t, 1> array{{0b01010101u}};
    constexpr auto result = isUTF8(std::cbegin(array), std::cend(array));
    REQUIRE(result);
}

TEST_CASE("Day 277: recognize an illegal single byte unicode character") {
    constexpr std::array<uint8_t, 1> array{{0b10010101u}};
    constexpr auto result = isUTF8(std::cbegin(array), std::cend(array));
    REQUIRE_FALSE(result);
}

TEST_CASE("Day 277: recognize a 2-byte unicode character") {
    constexpr std::array<uint8_t, 2> array{{0b11010101u, 0b10101010u}};
    constexpr auto result = isUTF8(std::cbegin(array), std::cend(array));
    REQUIRE(result);
}

TEST_CASE("Day 277: recognize an illegal 2-byte unicode character") {
    constexpr std::array<uint8_t, 2> array{{0b11010101u, 0b11101010u}};
    constexpr auto result = isUTF8(std::cbegin(array), std::cend(array));
    REQUIRE_FALSE(result);
}

TEST_CASE("Day 277: recognize a 3-byte unicode character") {
    constexpr std::array<uint8_t, 3> array{{0b11100101u, 0b10101010u, 0b10001111u}};
    constexpr auto result = isUTF8(std::cbegin(array), std::cend(array));
    REQUIRE(result);
}

TEST_CASE("Day 277: recognize a 4-byte unicode character") {
    constexpr std::array<uint8_t, 4> array{{0b11111101u, 0b10101010u, 0b10001111u, 0b10111111u}};
    constexpr auto result = isUTF8(std::cbegin(array), std::cend(array));
    REQUIRE(result);
}

TEST_CASE("Day 277: recognize a longer unicode string") {
    constexpr std::array<uint8_t, 11> array{{0b01111111u,
                                             0b11001010u, 0b10111100u,
                                             0b11101111u, 0b10000000u, 0b10101111u,
                                             0b11111010u, 0b10110101u, 0b10001010u, 0b10010110u,
                                             0b00000001u}};
    constexpr auto result = isUTF8(std::cbegin(array), std::cend(array));
    REQUIRE(result);
}