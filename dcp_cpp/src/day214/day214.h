/**
 * day214.h
 *
 * By Sebastian Raaphorst, 2020.
 */

#pragma once

#include <type_traits>

namespace dcp::day214 {
    template <typename T>
    constexpr std::enable_if_t<std::is_integral_v<T>, size_t> countConsecutive1s(T t) {
        size_t length = 0;

        // Every time we shift left (or right) and AND ourself, the size of the longest run must decrease by 1.
        // Thus, we can just continue shifting until t is 0.
        while (t) {
            ++length;
            t &= (t << 1);
        }

        return length;
    }
}