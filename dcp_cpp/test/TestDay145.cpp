/**
 * TestDay145.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <iostream>
#include <day145/day145.h>
using namespace dcp::day145;


TEST_CASE("Single linked list of length 0") {
    List<int> lst;
    lst.swapConsecutive();

    List<int> results;
    REQUIRE(lst == results);
}

TEST_CASE("Single linked list of length 1") {
    List<int> lst;
    lst.add(1);
    lst.swapConsecutive();

    List<int> results;
    results.add(1);
    REQUIRE(lst == results);
}

TEST_CASE("Single linked list of length 5") {
    List<int> lst;
    lst.addAll(5, 4, 3, 2, 1);
    lst.swapConsecutive();

    List<int> results;
    results.addAll(5, 3, 4, 1, 2);
    REQUIRE(lst == results);
}

TEST_CASE("Singly linked list of length 4") {
   List<int> lst;
   lst.addAll(4, 3, 2, 1);
   lst.swapConsecutive();

   List<int> results;
   results.addAll(3, 4, 1, 2);
   REQUIRE(lst == results);
}
