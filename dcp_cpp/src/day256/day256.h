/**
 * day256.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <algorithm>
#include <iterator>
#include <list>

namespace dcp::day256 {

    template<typename Iter>
    void alternate(Iter begin, Iter end) {
        auto size = std::distance(begin, end);
        if (size <= 2)
            return;

        int pos = 1;
        Iter curr = begin;
        advance(curr, 1);
        Iter currNext = begin;
        advance(currNext, 2);
        Iter prev = begin;

        while(pos < size) {
            // Swap behind if necessary.
            if (*prev > *curr)
                std::swap(*prev, *curr);
            // Swap ahead if necessary.
            if (pos + 1 < size && *currNext > *curr)
                std::swap(*currNext, *curr);
            advance(prev, 2);
            advance(curr, 2);
            advance(currNext, 2);
            pos += 2;
        }
    }
}
