/**
 * TestDay038.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <catch.hpp>
#include <array>
#include <iostream>
#include <tuple>

#include "day038/day038.h"

using namespace dcp::day038;
using namespace dcp::day038::impl;

constexpr int side = 5;

// Test the order of a symmetry.
template<size_t N, size_t Order, typename M>
bool has_order(const M &m) {
    // We check to see that if there is a pair (r,c) that actually requires the specified order.
    // This is to avoid, say, returning true for order of rot 180 having 4 when it should have 2.
    bool requires_order = Order == 1 ? true : false;

    // Check to see that the order of the symmetry is precisely Order.
    for (size_t r = 0; r < N; ++r) {
        for (size_t c = 0; c < N; ++c) {
            // Apply the function the appropriate number of times.
            // Keep track if it does not loop before the order.
            size_t r_apply = r;
            size_t c_apply = c;

            for (size_t order = 0; order < Order; ++order) {
                auto result = m(r_apply, c_apply);
                r_apply = result.first;
                c_apply = result.second;

                // If we have a loop that does not match the order, then we require the order if we work at all.
                if (order < Order && (r_apply != r || c_apply != c))
                    requires_order = true;
            }

            // Check that the order has been reached.
            if (r_apply != r || c_apply != c)
                return false;
        }
    }

    return requires_order;
}


/**
 * Check the values of the symmetries and their orders.
 */
TEST_CASE("Day038: Verify symm_id symmetry") {
    REQUIRE(has_order<2,1>(symm_id<side>));
    REQUIRE(has_order<3,1>(symm_id<side>));
    REQUIRE(has_order<4,1>(symm_id<side>));
    REQUIRE(has_order<5,1>(symm_id<side>));
    REQUIRE(has_order<2,1>(symm_id<side+1>));
    REQUIRE(has_order<3,1>(symm_id<side+1>));
    REQUIRE(has_order<4,1>(symm_id<side+1>));
    REQUIRE(has_order<5,1>(symm_id<side+1>));
}

TEST_CASE("Day038: Verify horizontal reflection symmetry") {
    REQUIRE(has_order<2,2>(symm_rh<side>));
    REQUIRE(has_order<3,2>(symm_rh<side>));
    REQUIRE(has_order<4,2>(symm_rh<side>));
    REQUIRE(has_order<5,2>(symm_rh<side>));
    REQUIRE(has_order<2,2>(symm_rh<side+1>));
    REQUIRE(has_order<3,2>(symm_rh<side+1>));
    REQUIRE(has_order<4,2>(symm_rh<side+1>));
    REQUIRE(has_order<5,2>(symm_rh<side+1>));
}

TEST_CASE("Day038: Verify vertical reflection symmetry") {
    REQUIRE(has_order<2,2>(symm_rv<side>));
    REQUIRE(has_order<3,2>(symm_rv<side>));
    REQUIRE(has_order<4,2>(symm_rv<side>));
    REQUIRE(has_order<5,2>(symm_rv<side>));
    REQUIRE(has_order<2,2>(symm_rv<side+1>));
    REQUIRE(has_order<3,2>(symm_rv<side+1>));
    REQUIRE(has_order<4,2>(symm_rv<side+1>));
    REQUIRE(has_order<5,2>(symm_rv<side+1>));
}

TEST_CASE("Day038: Verify slash reflection symmetry") {
    REQUIRE(has_order<2,2>(symm_sl<side>));
    REQUIRE(has_order<3,2>(symm_sl<side>));
    REQUIRE(has_order<4,2>(symm_sl<side>));
    REQUIRE(has_order<5,2>(symm_sl<side>));
    REQUIRE(has_order<2,2>(symm_sl<side+1>));
    REQUIRE(has_order<3,2>(symm_sl<side+1>));
    REQUIRE(has_order<4,2>(symm_sl<side+1>));
    REQUIRE(has_order<5,2>(symm_sl<side+1>));
}

TEST_CASE("Day038: Verify backslash reflection symmetry") {
    REQUIRE(has_order<2,2>(symm_bs<side>));
    REQUIRE(has_order<3,2>(symm_bs<side>));
    REQUIRE(has_order<4,2>(symm_bs<side>));
    REQUIRE(has_order<5,2>(symm_bs<side>));
    REQUIRE(has_order<2,2>(symm_bs<side+1>));
    REQUIRE(has_order<3,2>(symm_bs<side+1>));
    REQUIRE(has_order<4,2>(symm_bs<side+1>));
    REQUIRE(has_order<5,2>(symm_bs<side+1>));
}

TEST_CASE("Day038: Verify rotation by 90 symmetry") {
    REQUIRE(has_order<2,4>(rot_90<side>));
    REQUIRE(has_order<3,4>(rot_90<side>));
    REQUIRE(has_order<4,4>(rot_90<side>));
    REQUIRE(has_order<5,4>(rot_90<side>));
    REQUIRE(has_order<2,4>(rot_90<side+1>));
    REQUIRE(has_order<3,4>(rot_90<side+1>));
    REQUIRE(has_order<4,4>(rot_90<side+1>));
    REQUIRE(has_order<5,4>(rot_90<side+1>));
}

TEST_CASE("Day038: Verify rotation by 180 symmetry") {
    REQUIRE(has_order<2,2>(rot_180<side>));
    REQUIRE(has_order<3,2>(rot_180<side>));
    REQUIRE(has_order<4,2>(rot_180<side>));
    REQUIRE(has_order<5,2>(rot_180<side>));
    REQUIRE(has_order<2,2>(rot_180<side+1>));
    REQUIRE(has_order<3,2>(rot_180<side+1>));
    REQUIRE(has_order<4,2>(rot_180<side+1>));
    REQUIRE(has_order<5,2>(rot_180<side+1>));
}

TEST_CASE("Day038: Verify rotation by 270 symmetry") {
    REQUIRE(has_order<2,4>(rot_270<side>));
    REQUIRE(has_order<3,4>(rot_270<side>));
    REQUIRE(has_order<4,4>(rot_270<side>));
    REQUIRE(has_order<5,4>(rot_270<side>));
    REQUIRE(has_order<2,4>(rot_270<side+1>));
    REQUIRE(has_order<3,4>(rot_270<side+1>));
    REQUIRE(has_order<4,4>(rot_270<side+1>));
    REQUIRE(has_order<5,4>(rot_270<side+1>));
}

TEST_CASE("Day039: One queen") {
    constexpr auto n = NQueens<1>::n_queens();
    REQUIRE(n == 1);
}