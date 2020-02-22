/**
 * day321.h
 *
 * By Sebastian Raaphorst, 2020.
 *
 * Experiments based on day 321.
 */

#pragma once

#include <map>
#include <cmath>
#include <set>
#include <climits>

namespace dcp::day321 {
    using T = unsigned long long;

    std::map<T, T> cache{{1ULL, 0ULL}};

    /**
     * Given the sequence described in problem 321, find the first number that boosts the necessary moves by 1.
     */
    T stepToOne(T step) {
        const auto iter = cache.find(step);
        if (iter != cache.cend()) return iter->second;

        const auto ub = static_cast<T>(std::sqrt(step));
        std::set<T> divisors{};
        for (T it = 2ULL; it <= ub; ++it)
            if (step % it == 0ULL)
                divisors.insert(std::max(step / it, it));


        T min = 1ULL + stepToOne(step - 1ULL);
        for (auto d: divisors) {
            min = std::min(1ULL + stepToOne(d), min);
        }

        cache[step] = min;
        return min;
    }
}