/**
 * day177.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <list>

namespace dcp::day177 {
    template<typename T>
    std::list<T> &rotate_right(size_t k, std::list<T> &lst) {
        if (lst.empty())
            return lst;

        for (size_t i = 0; i < k; ++i) {
            auto &back = lst.back();
            lst.pop_back();
            lst.emplace_front(back);
        }

        return lst;
    }
}
