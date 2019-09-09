/**
 * day156.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <type_traits>
#include <limits>
#include <stdexcept>

namespace dcp::day156 {
    namespace Detail {
        double constexpr sqrtNewtonRaphson(double x, double curr, double prev) {
            return curr == prev ? curr : sqrtNewtonRaphson(x, 0.5 * (curr + x / curr), curr);
        }

        /**
         * Constexpr version of the square root
         * Return value:
         *   - For a finite and non-negative value of "x", returns an approximation for the square root of "x"
         *   - Otherwise, returns NaN
         */
        double constexpr sqrt(double x) {
            return x >= 0 && x < std::numeric_limits<double>::infinity()
                   ? Detail::sqrtNewtonRaphson(x, x, 0)
                   : std::numeric_limits<double>::quiet_NaN();
        }

        template<typename T>
        constexpr std::enable_if_t<std::is_integral_v<T>, bool> is_square(T x) {
            const auto rnd_root = static_cast<int>(sqrt(x));
            return rnd_root * rnd_root == x;
        }

        // Determine if an integer is of the form 4^k(8m + 7) for integers k, m.
        template<typename T>
        constexpr std::enable_if_t<std::is_integral_v<T>, bool> is_legendre(T x) {
            while (x % 4 == 0)
                x /= 4;
            return x % 8 == 7;
        }
    }

    // The Legendre method for calculating the number of required squares to sum to value.
    template<typename T>
    constexpr std::enable_if_t<std::is_integral_v<T>, int>
    legendre_num_squared_integers(const T value) {
        // Check corner cases.
        if (value == 0)
            return 0;
        if (Detail::is_square(value))
            return 1;

        // Check if a 2 exists.
        const auto approx_sqrt = sqrt(value);
        for (int i=1; i <= approx_sqrt; ++i)
            if (Detail::is_square(value - i * i))
                return 2;

        // Check if 4 via Legendre form.
        if (Detail::is_legendre(value))
            return 4;

        // Else by Lagrange, the answer must be 3.
        return 3;
    }

    // Determine the minimal number of squares needed to write a number as a sum of integral squares using a
    // brute-force approach.
    template<typename T>
    constexpr std::enable_if_t<std::is_integral_v<T>, int> brute_force(T value) {
        // Terminal conditions: is zero.
        if (value < 0)
            throw std::range_error("Negative number");

        if (value == 0)
            return 0;
        if (Detail::is_square(value))
            return 1;

        // Try everything up to the sqrt, trying to find the minimum number of iterations.
        const T sqrtv = sqrt(value);
        int sqrs_needed = -1;
        for (T i = 1; i <= sqrtv; ++i) {
            // Calculate the number of squares needed.
            int sqrs_needed_iter = brute_force(value - i * i) + 1;
            if (sqrs_needed == -1 || sqrs_needed_iter < sqrs_needed)
                sqrs_needed = sqrs_needed_iter;
        }

        return sqrs_needed;
    }
}
