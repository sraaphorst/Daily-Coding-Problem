/**
 * day004.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <iostream>
#include <cmath>
#include <iterator>
#include <type_traits>
#include <vector>

namespace dcp::day004 {
    // We need a random access iterator for this to work in-place since we need constant space.
    template<typename Iter>
    std::enable_if_t<std::is_integral_v <typename std::iterator_traits<Iter>::value_type>
                     && std::is_signed_v<typename std::iterator_traits<Iter>::value_type>
                     && std::is_same_v  <typename std::iterator_traits<Iter>::iterator_category, std::random_access_iterator_tag>,
                     typename std::iterator_traits<Iter>::value_type>
    find_lowest_missing_positive(Iter&& begin, Iter&& end) {
        const auto N = std::distance(begin, end);
        //using iter_type = typename std::iterator_traits<Iter>::value_type;

        // If the container has size 1, we view this as a special case to avoid unnecessarily complex iterator
        // manipulation later on.
        if (N == 1)
            return (*begin == 1) ? 2 : 1;

        // First we move all non-positive entries to the far right of the structure.
        // At the end of this, e should be pointing at the last positive containing index.
        auto b = begin;
        auto e = end - 1;

        while (b != e) {
            if (*b <= 0) {
                std::iter_swap(b, e);
                --e;
            } else
                ++b;
        }

        std::cerr << "Separation: ";
        for (auto bp = begin; bp != end; ++bp)
            std::cerr << *bp << " ";
        std::cerr << '\n';

        // We want it pointing at the first non-positive containing index, if any.
        ++e;

        // Observe first that we only care about positive integers and not 0, so we un-offset by 1.
        // Now, the strategy is to hack the collection and mark a position as filled by making it negative.
        // So if we find a 1, make sure the contents in position 0 are negative.
        for (b = begin; b != e; ++b) {
            // Check what element this covers and mark the index as negative.
            const auto elem = std::abs(*b) - 1;

            // Make sure it fits, and only flip it if it isn't already negative.
            auto iter = begin + elem;
            if (elem <= N && *iter > 0)
                *iter = - *iter;
        }

        // The first non-negative value is what is not covered, up to e.
        size_t i = 0;
        for (; b + i != e; ++i) {
            if (*(b + i) >= 0)
                return i + 1;
        }

        // If we made it this far, everything up to e is covered, so the next element is missing.
        return i + 1;
    }
}
