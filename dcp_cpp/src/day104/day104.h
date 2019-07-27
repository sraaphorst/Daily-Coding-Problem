/**
 * day104.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <algorithm>
#include <iterator>
#include <stack>

namespace dcp::day104 {
    template <typename Iter>
    bool is_dll_palindrome(Iter &&begin, Iter &&end) {
        const auto a = *begin;
        const auto b = *--end;
        if (a != b)
            return false;
        if (begin == end)
            return true;
        return is_dll_palindrome(++begin, end);
    }

    template <typename Iter>
    bool is_sll_palindrome(Iter &&begin, Iter &&end) {
        using iter_type = typename std::iterator_traits<Iter>::value_type;

        // Invert all characters into a stack.
        std::stack<iter_type> stk;
        std::for_each(begin, end, [&stk](const auto c) { stk.push(c); });

        // Guaranteed to have same size: pop and compare.
        for (; begin != end; ++begin) {
            const auto c = stk.top();
            stk.pop();
            if (*begin != c)
                return false;
        }

        return true;
    }
}
