/**
 * day118.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <array>
#include <iterator>

namespace dcp::day118 {
    /**
     * Given a sorted array of type int, determine how many negative elements there are in it.
     */
    template<size_t N>
    constexpr auto neg_element_length(const std::array<int, N> &arr) {
        for (size_t i = 0; i < N; ++i)
            if (arr[i] >= 0)
                return i;
        return N;
    }

    /**
     * Given an array, square the elements of it.
     */
    template<size_t N>
    constexpr auto square(const std::array<int, N> &arr) {
        std::array<int, N> arr2{};
        for (size_t i = 0; i < N; ++i)
            arr2[i] = arr[i] * arr[i];
        return arr2;
    }

    /**
     * Given an array of type T of size N, reverse it.
     * This is essential here because the squares of the negative elements will be positive and in reverse order.
     */
    template<typename T, size_t N>
    constexpr auto reverse(const std::array<T, N> &arr) {
        std::array<T, N> arr_rev{};
        for (size_t i = 0; i < N; ++i)
            arr_rev[i] = arr[N - i - 1];
        return arr_rev;
    }

    /**
     * Get the first F elements out of an array of type T of length N.
     */
    template<size_t F, typename T, size_t N>
    constexpr std::array<T, F> first_elements(const std::array<T, N> &arr) {
        std::array<T, F> arr_sub{};
        for (size_t i = 0; i < F; ++i)
            arr_sub[i] = arr[i];
        return arr_sub;
    }

    /**
     * Get the last L elements out of an array of type T of length N.
     */
    template<size_t L, typename T, size_t N>
    constexpr std::array<T, L> last_elements(const std::array<T, N> &arr) {
        std::array<T, L> arr_sub{};
        for (size_t i = 0; i < L; ++i)
            arr_sub[i] = arr[i + N - L];
        return arr_sub;
    }

    /**
     * Merge together two sorted arrays into one sorted array.
     * Note we SFINAE if T is not ordered.
     */
    template<typename T, size_t N1, size_t N2>
    constexpr std::array<T, N1 + N2> merge(const std::array<T, N1> &arr1, const std::array<T, N2> &arr2) {
        std::array<T, N1 + N2> arr_merge{};

        size_t i1 = 0;
        size_t i2 = 0;
        while (i1 + i2 < N1 + N2) {
            // Note that we have to be REALLY careful here to convince the compiler that we are not reading past the end
            // of the arrays for this to compile as constexpr, hence the extraneous checks if the if statements.
            const auto idx = i1 + i2;
            if (i2 == N2 || (i1 < N1 && arr1[i1] <= arr2[i2])) {
                arr_merge[idx] = arr1[i1++];
            } else if (i1 == N1 || (i2 < N2 && arr2[i2] <= arr1[i1])) {
                arr_merge[idx] = arr2[i2++];
            }
        }
        return arr_merge;
    }
}