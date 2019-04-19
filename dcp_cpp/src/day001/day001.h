/**
 * day001.cpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <array>
#include <iostream>
#include <type_traits>
#include <unordered_map>
#include <vector>

namespace dcp::day001 {
    template<typename T, typename Iter>
    std::enable_if_t<std::is_arithmetic_v<T>, bool>
    contains_sum(T sum, Iter begin, Iter end) {
        // Create unordered map of elements with their counts. This is important as it has average constant complexity,
        // which allows the search to have complexity O(n) instead of the naive O(n^2) approach.
        // The counts are essential so that we don't end up with situations where our sum is 2n for some n in the list
        // that appears only once.
        std::unordered_map<T, int> vals{};
        for (; begin != end; ++begin)
            vals[*begin] += 1;

        for (const auto[v, ct]: vals) {
            if (sum - v == v) {
                if (ct >= 2)
                    return true;
            } else if (vals.find(sum - v) != vals.cend())
                return true;
        }

        return false;
    }
}
