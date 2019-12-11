/**
 * day248.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <type_traits>

namespace dcp::day248 {
    /**
     * Find the maximum of two numbers.
     * This can fail with numbers whose absolute value is too large, so we limit testing to max / 2.
     */
    template<typename T>
    typename std::enable_if<std::is_integral<T>::value, T>::type
    constexpr max(T a, T b) {
        // Check the uppermost bit to exploit the 2s complement.
        // If a > b, then d = 0; else d = 1.
        T d = (a - b) >> (sizeof(T) * 8 - 1) & 1;
        return (1 - d) * a + d * b;
    }
}