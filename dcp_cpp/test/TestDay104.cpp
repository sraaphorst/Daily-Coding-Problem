/**
 * TestDay104.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <list>
#include <forward_list>

#include <day104/day104.h>
using namespace dcp::day104;

TEST_CASE("A DLL empty palindrome is recognized") {
    std::list<char> lst{};
    REQUIRE(lst.empty());
    REQUIRE(is_dll_palindrome(std::begin(lst), std::end(lst)));
}

TEST_CASE("A DLL palindrome of off length is recognized") {
    std::list<char> lst = {'R', 'A', 'D', 'A', 'R'};
    REQUIRE(lst.size() % 2 == 1);
    REQUIRE(is_dll_palindrome(std::begin(lst), std::end(lst)));
}

TEST_CASE("A DLL palindrome of even length is recognized") {
    std::list<char> lst = {'t', 'a', 't', 't', 'a', 'r', 'r', 'a', 't', 't', 'a', 't'};
    REQUIRE(lst.size() % 2 == 0);
    REQUIRE(is_dll_palindrome(std::begin(lst), std::end(lst)));
}

TEST_CASE("A SLL empty palindrome is recognized") {
    std::forward_list<char> lst{};
    REQUIRE(lst.empty());
    REQUIRE(is_sll_palindrome(std::begin(lst), std::end(lst)));
}

TEST_CASE("A SLL palindrome of off length is recognized") {
    std::forward_list<char> lst = {'R', 'A', 'D', 'A', 'R'};
    REQUIRE(is_sll_palindrome(std::begin(lst), std::end(lst)));
}

TEST_CASE("A SLL palindrome of even length is recognized") {
    std::forward_list<char> lst = {'t', 'a', 't', 't', 'a', 'r', 'r', 'a', 't', 't', 'a', 't'};
    REQUIRE(is_sll_palindrome(std::begin(lst), std::end(lst)));
}
