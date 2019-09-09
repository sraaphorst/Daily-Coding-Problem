/**
 * day155.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <unordered_map>
#include <algorithm>
#include <optional>
#include <iterator>
#include <type_traits>

namespace dcp::day155 {
    template<typename Iter, typename T = typename std::iterator_traits<Iter>::value_type>
    std::optional<T> find_majority_element(const Iter begin, const Iter end) {
        // Counts the elements properly.
        std::unordered_map<T, size_t> counts{};
        std::for_each(begin, end, [&counts] (const auto elem) {
            ++counts[elem];
        });

        // Determine the goal.
        const auto goal = std::floor(std::distance(begin, end) / 2.0);

        // Now find the (possible) element that appears above the median times by getting its iterator.
        const auto iter = std::find_if(std::cbegin(counts), std::cend(counts), [&goal](const auto iter) {
           return iter.second >= goal;
        });

        // Determine if value exists and convert to optional..
        if (iter == std::cend(counts))
            return {};
        return {iter->first};
    }
}
