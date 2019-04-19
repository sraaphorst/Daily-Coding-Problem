/**
 * day002.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <iterator>
#include <type_traits>
#include <vector>

namespace dcp::day002 {
    template<typename Iter1, typename Iter2>
    std::enable_if_t<std::is_arithmetic_v<typename std::iterator_traits<Iter1>::value_type>
            && std::is_same_v<typename std::iterator_traits<Iter1>::value_type,
                              typename std::iterator_traits<Iter2>::value_type>,
            void>
    calculate_products(const Iter1& begin1, const Iter1& end1, Iter2&& begin2) {
        const size_t N = std::distance(begin1, end1);
        using iter_type = typename std::iterator_traits<Iter1>::value_type;

        // Create temporary arrays to calculate the product from the left and the product from the right.
        // For notational simplicity in comments, say that begin1 and end1 are a random access container arr
        // of length n. Then we want:
        // left[i]  = arr[0]   * ... * arr[i-1]
        // right[i] = arr[n-1] * ... * arr[i+1]
        // Then result[i] = left[i] * right[i].
        std::vector<iter_type> left(N);
        std::vector<iter_type> right(N);

        for (size_t i = 0; i < N; ++i) {
            left[i]      = i ? left[i-1]  * *(begin1 + i-1) : 1;
            right[N-i-1] = i ? right[N-i] * *(begin1 + N-i) : 1;
        }

        for (size_t i = 0; i < N; ++i)
            *(begin2 + i) = left[i] * right[i];
    }
}