// day208.h
// By Sebastian Raaphorst, 2019.

#pragma once

#include <array>

namespace dcp::day208 {
    constexpr size_t collatz(const size_t n) {
        // Should only be defined for positive integers; otherwise, it will loop indefinitely.
        if (n == 0)
            return 0;
        if (n == 1)
            return 0;
        if (n % 2 == 0)
            return 1 + collatz(n / 2);
        else
            return 1 + collatz(3 * n + 1);
    }

    constexpr size_t collatz_max_up_to(const size_t N) {
        size_t longest = 1;
        size_t longestValue = 1;
        for (size_t i = 1; i < N; ++i) {
            auto ciLength = collatz(i);
            if (ciLength > longestValue) {
                longestValue = ciLength;
                longest = i;
            }
        }
        return longest;
    }

    template<size_t N>
    constexpr std::array<size_t, N> populate_collatz() {
        std::array<size_t, N> collatz_values{};
        for (size_t i = 0; i < N; ++i)
            collatz_values[i] = collatz(i);
        return collatz_values;
    }
}
