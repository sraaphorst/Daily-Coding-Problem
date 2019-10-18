/**
 * TestDay154.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>

#include <day154/day154.h>
using namespace dcp::day154;

TEST_CASE("Empty heap") {
    HeapStack<int> empty{};
    REQUIRE_THROWS(empty.pop());
}

TEST_CASE("Specific heap") {
    HeapStack<int> heap{};
    heap.push(1);
    heap.push(3);
    heap.push(2);
    heap.push(7);
    heap.push(5);
    REQUIRE(heap.pop() == 5);
    REQUIRE(heap.pop() == 7);
    REQUIRE(heap.pop() == 2);
    REQUIRE(heap.pop() == 3);
    REQUIRE(heap.pop() == 1);
    REQUIRE_THROWS(heap.pop());
}

TEST_CASE("Divided into two") {
    HeapStack<int> heap{};
    heap.push(1);
    heap.push(3);
    heap.push(2);
    heap.push(7);
    heap.push(5);
    REQUIRE(heap.pop() == 5);
    REQUIRE(heap.pop() == 7);
    REQUIRE(heap.pop() == 2);
    heap.push(9);
    heap.push(8);
    heap.push(10);
    REQUIRE(heap.pop() == 10);
    REQUIRE(heap.pop() == 8);
    REQUIRE(heap.pop() == 9);
    REQUIRE(heap.pop() == 3);
    REQUIRE(heap.pop() == 1);
    REQUIRE_THROWS(heap.pop());
}