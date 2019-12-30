/**
 * TestDay268.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <vector>

#include <day268/day268.h>
using namespace dcp::day268;

TEST_CASE("Day 268: Check several powers of 4.") {
    constexpr auto f0 = isPowerOf4(1u);
    REQUIRE(f0);

    constexpr auto f1 = isPowerOf4(4u);
    REQUIRE(f1);

    constexpr auto f2 = isPowerOf4(16u);
    REQUIRE(f2);

    constexpr auto f3 = isPowerOf4(64u);
    REQUIRE(f3);

    constexpr auto f4 = isPowerOf4(256u);
    REQUIRE(f4);
}

TEST_CASE("Day 268: Check several numbers that are sums of powers of 4.") {
    constexpr auto f0 = isPowerOf4(4u + 16u);
    REQUIRE_FALSE(f0);

    constexpr auto f1 = isPowerOf4(16u + 64u + 256u);
    REQUIRE_FALSE(f1);

    constexpr auto f2 = isPowerOf4(1u + 256u);
    REQUIRE_FALSE(f2);

    constexpr auto f3 = isPowerOf4(1u + 4u);
    REQUIRE_FALSE(f3);

    // 4^0 + ... + 4^15, the maximum value we can store in an unsigned 32 bit int.
    constexpr auto f4 = isPowerOf4(0x55555555u);
    REQUIRE_FALSE(f4);
}

TEST_CASE("Day 268: Loop over subset of integers and make sure only powers of four are selected.") {
    std::vector<uint32_t> expected { 1u, 4u, 16u, 64u, 256u, 1024u, 4096u, 16384u }; // 4^0, ..., 4^7.
    std::vector<uint32_t> found{};
    for (uint32_t i = 0u; i < 16385u; ++i)
        if (isPowerOf4(i))
            found.push_back(i);
    REQUIRE(found == expected);
}