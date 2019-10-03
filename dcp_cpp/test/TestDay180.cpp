/**
 * TestDay180.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <vector>
#include <stack>

#include <day180/day180.h>
using namespace dcp::day180;

TEST_CASE("Day180: Example test case 1") {
    std::stack<int> stack{{1, 2, 3, 4, 5}};
    const std::stack<int> result{{1, 5, 2, 4, 3}};
    interleave(stack);
    REQUIRE(stack == result);
}

TEST_CASE("Day180: Example test case 2") {
    std::stack<int> stack{{1, 2, 3, 4}};
    const std::stack<int> result{{1, 4, 2, 3}};
    interleave(stack);
    REQUIRE(stack == result);
}

TEST_CASE("Day180: With strings") {
    std::stack<std::string> stack{{"max", "duncan", "punz", "felix", "kali", "oliver"}};
    const std::stack<std::string> result{{"max", "oliver", "duncan", "kali", "punz", "felix"}};
    interleave(stack);
    REQUIRE(stack == result);
}

TEST_CASE("Day180: One element stack") {
    std::stack<int> stack{{0}};
    const std::stack<int> result{{0}};
    interleave(stack);
    REQUIRE(stack == result);
}

TEST_CASE("Day180: Empty stack") {
    std::stack<int> stack{};
    std::stack<int> result{};
    interleave(stack);
    REQUIRE(stack == result);
}