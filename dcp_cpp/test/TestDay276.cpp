/**
 * TestDay276.cpp
 *
 * By Sebastian Raaphorst, 2020.
 */

#include <catch.hpp>
#include <vector>

#include <day276/day276.h>
using namespace dcp::day276;

static const std::string y = "Smoking is very very bad for you. You shouldn't do it because it's very bad for you. "
                      "Drinking to excess is also very very bad for you, but lots of people do it even though they "
                      "know that it is bad for you. When things are very bad for you, they are likely "
                      "to shorten your lifespan.";

TEST_CASE("Day 276: very very bad for you") {
    const auto x = "very very bad for you";
    const auto results = search(x, y);
    REQUIRE(results == std::vector{11, 112});
}

TEST_CASE("Day 276: very bad for you") {
    const auto x = "very bad for you";
    const auto results = search(x, y);
    REQUIRE(results == std::vector{16, 67, 117, 222});
}

TEST_CASE("Day 276: bad for you") {
    const auto x = "bad for you";
    const auto results = search(x, y);
    REQUIRE(results == std::vector{21, 72, 122, 193, 227});
}

TEST_CASE("Day 276: for") {
    const auto x = "for";
    const auto results = search(x, y);
    REQUIRE(results == std::vector{25, 76, 126, 197, 231});
}
