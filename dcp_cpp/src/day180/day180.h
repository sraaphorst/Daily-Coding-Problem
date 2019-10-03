/**
 * day177.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <stack>
#include <queue>

namespace dcp::day180 {
    /**
     * Interleave the first half of a stack with the second half reversed.
     * Examples:
     * - 1 2 3 4 5 should become 1 5 2 4 3.
     * - 1 2 3 4 should become 1 4 2 3.
     *
     * @tparam T The data type of the stack
     * @param stack the stack to interleave
     */
    template<typename T>
    void interleave(std::stack<T> &stack) {
        if (stack.empty())
            return;

        // The idea is as follows:
        // Enqueue all elements but the first.
        // Push all the elements back on the stack (they will be reversed).
        // Enqueue all the elements but the first two.
        // Push all the elements back on the stack (they will be in original order).
        // Continue this pattern until there is only one element left.
        std::queue<T> queue;

        for (size_t elements_to_enqueue = stack.size() - 1; elements_to_enqueue > 1; --elements_to_enqueue) {
            for (size_t i = 0; i < elements_to_enqueue; ++i) {
                T elem = std::move(stack.top());
                stack.pop();
                queue.push(std::move(elem));
            }
            while (!queue.empty()) {
                T elem = std::move(queue.front());
                queue.pop();
                stack.push(std::move(elem));
            }
        }
    }
}
