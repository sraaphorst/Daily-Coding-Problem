/**
 * TestDay223.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <vector>

#include <day223/day223.h>
using namespace dcp::day223;

NodePtr<int> n(int value) {
    return std::make_shared<Node<int>>(value);
}

// Don't pass by reference so that we can be nullptr test case.
std::vector<int> collect(NodePtr<int> root) {
    const auto threaded = createThreadedBinaryTree(root);
    std::vector<int> collected;
    inOrderTraversal(root, [&collected](auto v) { collected.emplace_back(v); });
    return collected;
}

TEST_CASE("Day 224: Nine node tree") {
    auto n6 = n(6);
    auto n3 = n(3);
    auto n1 = n(1);
    auto n5 = n(5);
    auto n8 = n(8);
    auto n7 = n(7);
    auto n11 = n(11);
    auto n9 = n(9);
    auto n10 = n(10);
    auto n13 = n(13);

    n6->left = n3;
    n3->left = n1;
    n3->right = n5;
    n6->right = n8;
    n8->left = n7;
    n8->right = n11;
    n11->left = n9;
    n11->right = n13;

    REQUIRE(collect(n6) == std::vector{1, 3, 5, 6, 7, 8, 9, 11, 13});
}

TEST_CASE("Day 224: All right tree") {
    auto n1 = n(1);
    auto n2 = n(2);
    auto n3 = n(3);
    auto n4 = n(4);
    auto n5 = n(5);
    n1->right = n2;
    n2->right = n3;
    n3->right = n4;
    n4->right = n5;

    REQUIRE(collect(n1) == std::vector{1, 2, 3, 4, 5});
}

TEST_CASE("Day 224: All left tree") {
    auto n5 = n(5);
    auto n4 = n(4);
    auto n3 = n(3);
    auto n2 = n(2);
    auto n1 = n(1);
    n5->left = n4;
    n4->left = n3;
    n3->left = n2;
    n2->left = n1;

    REQUIRE(collect(n5) == std::vector{1, 2, 3, 4, 5});
}

TEST_CASE("Day 224: One node tree") {
    auto n1 = n(1);
    REQUIRE(collect(n1) == std::vector{1});
}

TEST_CASE("Day 224: Empty tree") {
    REQUIRE(collect(nullptr).empty());
}

TEST_CASE("Day 224: Empty right") {
    auto n1 = n(1);
    auto n2 = n(2);
    auto n3 = n(3);
    n1->right = n2;
    n2->left = n3;
    REQUIRE(collect(n1) == std::vector{1, 3, 2});
}

TEST_CASE("Day 224: Big, convoluted tree") {
    std::vector<NodePtr<int>> ns;
    for (int i = 0; i < 18; ++i)
        ns.emplace_back(n(i+1));
    ns[0]->right = ns[1];
    ns[2]->left = ns[0];
    ns[2]->right = ns[4];
    ns[4]->left = ns[3];
    ns[4]->right = ns[5];
    ns[6]->left = ns[2];
    ns[6]->right = ns[8];
    ns[8]->left = ns[7];
    ns[9]->left = ns[6];
    ns[9]->right = ns[10];
    ns[10]->right = ns[11];
    ns[11]->right = ns[15];
    ns[15]->left = ns[13];
    ns[15]->right = ns[17];
    ns[13]->left = ns[12];
    ns[13]->right = ns[14];
    ns[17]->left = ns[16];

    REQUIRE(collect(ns[9]) == std::vector{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18});
}