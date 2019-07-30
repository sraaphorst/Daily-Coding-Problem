/**
 * TestDay114.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <string>

#include <day114/day114.h>
using namespace dcp::day114;

TEST_CASE("Test empty string juggle_rotate") {
    std::string s = "";
    juggle_rotation(s, 5);
    REQUIRE(s == "");
}

TEST_CASE("Test length 1 string juggle_rotate") {
    std::string s = "1";
    juggle_rotation(s, 2);
    REQUIRE(s == "1");
}

TEST_CASE("Test single short juggle_rotate") {
    std::string s = "Hi there";
    const std::string result = " thereHi";
    juggle_rotation(s, 2);
    REQUIRE(s == result);
}

TEST_CASE("Test single long juggle_rotate") {
    std::string s = "Reverse#Strings Without%Changing-Delimiters";
    const std::string result = "ersReverse#Strings Without%Changing-Delimit";

    // String length is 43.
    // Rotate right by length(last_word) - length(first_word) = 10 - 7 = 3
    // So rotate left by 40.
    juggle_rotation(s, 40);
    REQUIRE(s == result);
}
TEST_CASE("Test substrings juggle_rotate") {
    std::string s = "ersReverse#Strings Without%Changing-Delimit";
    const std::string result = "Delimiters#Strings Without%Changing-Reverse";
    // 17 is the length of the substring, and 7 is the length of the first word.
    // Since we are rotating left instead of right, instead of rotating by 7, rotate by 17 - 7 = 10.
    // This is just the length of the second word.
    juggle_rotation(s, 0, 9, 36, 42, 10);
    REQUIRE(s == result);
}
