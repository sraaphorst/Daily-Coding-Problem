/**
 * day154.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <algorithm>
#include <stdexcept>
#include <tuple>
#include <vector>
#include <bits/stdc++.h>

namespace dcp::day154 {
    template <typename T>
    class HeapStack final {
    public:
        using data_type = std::pair<int, T>;
        std::priority_queue<data_type> pq;
        int num_elements = 0;

    public:
        HeapStack() = default;

        void push(const T item) noexcept {
            pq.push(data_type(num_elements++, item));
        }

        T pop() {
            if (pq.empty())
                throw std::out_of_range("No more elements in stack");
            data_type temp = pq.top();
            pq.pop();
            return temp.second;

        }
    };
}