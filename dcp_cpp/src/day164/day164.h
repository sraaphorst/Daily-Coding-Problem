/**
 * day164.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <algorithm>
#include <iterator>
#include <optional>
#include <unordered_set>

namespace dcp::day164 {
    // Apply the pigeonhole principle to return a repeated element.
    template<typename T, typename Iter>
    std::optional<T> apply_pidgeonhole(Iter begin, Iter end) {
        std::unordered_set<T> pidgeonholes;

        // Use for loop instead of for_each to prematurely terminate if we encounter a duplicated item.
        for (auto iter = begin; iter < end; ++iter) {
            // We already found the element, so terminate with the duplicate.
            if (std::find(std::begin(pidgeonholes), std::end(pidgeonholes), *iter) != std::cend(pidgeonholes))
                return {*iter};
            pidgeonholes.insert(*iter);
        }

        return {};
    }

    // If we read this point, then we have a problem: there is no repeated element.
}
