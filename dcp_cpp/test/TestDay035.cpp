/**
 * TestDay035.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <string>

#include "day035/day035.h"

using namespace dcp::day035;

TEST_CASE("Day035: Empty case") {
    std::string str{};
    REQUIRE(arrange_rgb_string(str).empty());
}

TEST_CASE("Day035: All Rs") {
    std::string str{"RRRRR"};
    REQUIRE(arrange_rgb_string(str) == "RRRRR");
}

TEST_CASE("Day035: All Gs") {
    std::string str{"GGGGGG"};
    REQUIRE(arrange_rgb_string(str) == "GGGGGG");
}

TEST_CASE("Day035: All Bs") {
    std::string str{"BBBB"};
    REQUIRE(arrange_rgb_string(str) == "BBBB");
}

TEST_CASE("Day035: Example") {
    std::string str{"GBRRBRG"};
    REQUIRE(arrange_rgb_string(str) == "RRRGGBB");
}

TEST_CASE("Day035: Backwards") {
    std::string str{"BBBBGGGGGGRRRRR"};
    REQUIRE(arrange_rgb_string(str) == "RRRRRGGGGGGBBBB");
}

TEST_CASE("Day035: Complex example") {
    std::string str{"GBBGRBBRRGBGBBBBGRRGBBRG"};
    REQUIRE(arrange_rgb_string(str) == "RRRRRRGGGGGGGBBBBBBBBBBB");
}