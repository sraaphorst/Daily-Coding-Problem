/**
 * day012.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <iostream>
#include <iterator>
#include <map>
#include <tuple>
#include <numeric>

namespace dcp::day012 {
    /**
     * Returns the number of unique step configurations to climb goal steps using the possible steps indicated in
     * the collection given by the integers.
     * Takes a cache in order to cache the results, so that calls such as fib(n) don't blow up exponentially.
     */
    template<typename T, typename Iter>
    std::enable_if_t<std::is_integral_v<T> && std::is_unsigned_v<T>, T>
    unique_steps(const T goal, const Iter &begin, const Iter &end, std::map<T, T> &cache) {

        if (goal == 0)
            return 1;

        // If cached, return the cached value.
        const auto cached = cache.find(goal);
        if (cached != std::cend(cache))
            return cached->second;

        // Otherwise, compute, cache, and return.
        const auto result = std::accumulate(begin, end, T{}, [&](const auto acc, const auto curr) {
            // Avoid underflowing.
            if (goal < curr)
                return acc;
            return acc + unique_steps(goal - curr, begin, end, cache);
        });
        cache[goal] = result;

        return result;
    }

    /**
     * In this variation, we create a cache and send it into the function.
     * Returns the number of unique step configurations to climb goal steps using the possible steps indicated in
     * the collection given by the integers.
     */
    template<typename T, typename Iter>
    std::enable_if_t<std::is_integral_v<T> && std::is_unsigned_v<T>, T>
    unique_steps(const T goal, const Iter &begin, const Iter &end) {
        std::map<T, T> cache{};
        return unique_steps(goal, begin, end, cache);
    }
}