/**
 * day184.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <type_traits>

namespace dcp::day184 {
    namespace details {
        template<typename T>
        constexpr auto abs(T t) {
            static_assert(std::is_arithmetic_v<T>);
            return t >= 0 ? t : -t;
        }
    }

    // Non-variadic template for gcd with 2 parameters.
    template<typename T, typename S>
    constexpr auto gcd(T t, S s) {
        static_assert(std::is_integral_v<T>);
        static_assert(std::is_integral_v<S>);
        if (s == 0)
            return details::abs(t);
        return gcd(details::abs(s), details::abs(t % s));
    }

    // Variadic template to extend gcd to any number of parameters >= 2.
    template<typename T, typename S, typename... Types>
    constexpr auto gcd(T t, S s, Types... args) {
        static_assert(std::is_integral_v<T>);
        static_assert(std::is_integral_v<S>);
        return gcd(details::abs(t), details::abs(gcd(details::abs(s), args...)));
    }
}
