/**
 * day140.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <array>
#include <iterator>
#include <tuple>

namespace dcp::day140 {
    // We no longer use reduce or accumulate since they are not constexpr.
    template<typename Iter, typename T = typename std::iterator_traits<Iter>::value_type>
    constexpr std::pair<T, T> find_two_nonrepeating_elements(const Iter begin, const Iter end) {
        static_assert(std::is_integral_v<T>);

        // Take the XOR of all elements. The bits that are set will be set by one of the two non-repeating elements
        // of array.
        T xors{};
        for (auto iter = begin; iter != end; ++iter)
            xors ^= *iter;

        // We want to find a set bit and then divide the collection into two:
        // 1. One with all of that bit set.
        // 2. One with none of that bit set.
        // Then each of these collections will contain one of the two non-repeating elements, so we can xor the contents
        // of each collection together. The doubled elements will eliminate via XOR, and all that will be left are the
        // non-repeating elements, one for each set.
        const T set_bit = xors & ~(xors - 1);
        T elem1{};
        T elem2{};
        for (auto iter = begin; iter != end; ++iter)
            if (*iter & set_bit)
                elem1 ^= *iter;
            else
                elem2 ^= *iter;
        return std::make_pair(elem1, elem2);
    }
}