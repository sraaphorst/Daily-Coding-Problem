/**
 * TestDay113.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <day113/day113.h>
using namespace dcp::day113;

TEST_CASE("swap_words on an even-length string") {
    std::string s = "hello to you";
    swap_words(s);
    REQUIRE(s == "you to hello");
}

TEST_CASE("swap_words on an odd-length string") {
    std::string s = "cats are people too";
    swap_words(s);
    REQUIRE(s == "too people are cats");
}
