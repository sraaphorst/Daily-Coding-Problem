/**
 * day184.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <type_traits>

namespace dcp::day184 {
    // Non-variadic template for gcd with 2 parameters.
    template<typename T, typename S>
    constexpr auto gcd(T t, S s) {
        static_assert(std::is_integral_v<T>);
        static_assert(std::is_integral_v<S>);
        if (s == 0)
            return t;
        return gcd(s, t % s);
    }

    // Variadic template to extend gcd to any number of parameters >= 2.
    template<typename T, typename S, typename... Types>
    constexpr auto gcd(T t, S s, Types... args) {
        static_assert(std::is_integral_v<T>);
        static_assert(std::is_integral_v<S>);
        return gcd(t, gcd(s, args...));
    }
}
