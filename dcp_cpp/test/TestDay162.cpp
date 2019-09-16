/**
 * TestDay162.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <array>
#include <set>
#include <string>

#include <day162/day162.h>
using namespace dcp::day162;

TEST_CASE("Collect unique prefixes for dotty, dog, and ditch") {
    const std::array<std::string, 3> strs{{"dotty", "dog", "ditch"}};
    const auto prefixes = create_minimal_representation(std::cbegin(strs), std::cend(strs));
    REQUIRE(prefixes.size() == 3);
    REQUIRE(prefixes.find("dot") != std::cend(prefixes));
    REQUIRE(prefixes.find("dog") != std::cend(prefixes));
    REQUIRE(prefixes.find("di") != std::cend(prefixes));
}

TEST_CASE("Collect unique prefixes for dog, cat, apple, apricot, and fish") {
    const std::array<std::string, 5> strs{{"dog", "cat", "apple", "apricot", "fish"}};
    const auto prefixes = create_minimal_representation(std::cbegin(strs), std::cend(strs));
    REQUIRE(prefixes.size() == 5);
    REQUIRE(prefixes.find("d") != std::cend(prefixes));
    REQUIRE(prefixes.find("c") != std::cend(prefixes));
    REQUIRE(prefixes.find("app") != std::cend(prefixes));
    REQUIRE(prefixes.find("apr") != std::cend(prefixes));
    REQUIRE(prefixes.find("f") != std::cend(prefixes));
}

TEST_CASE("Maintain prefixes for overlapping words") {
    const std::array<std::string, 3> strs{{"cat", "catheter", "cathinone"}};
    const auto prefixes = create_minimal_representation(std::cbegin(strs), std::cend(strs));
    REQUIRE(prefixes.size() == 3);
    REQUIRE(prefixes.find("cat") != std::cend(prefixes));
    REQUIRE(prefixes.find("cathe") != std::cend(prefixes));
    REQUIRE(prefixes.find("cathi") != std::cend(prefixes));
}