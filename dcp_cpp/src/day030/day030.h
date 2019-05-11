/**
 * day030.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <array>
#include <iostream>

namespace dcp::day030 {
    /**
     * Given an elevation chart, determine how many units of rain can be retained.
     */
    template<size_t N>
    constexpr size_t captured_water(const std::array<size_t, N> &elevation) {
        /**
         * We first want to go through the elevation and determine the maximal peaks.
         * Example:
         *
         *    #   #
         *    # # #
         *    1 2 3
         *
         * In this case, while 1, 2 and 3 are all peaks, 1 and 3 are maximal peaks, since peak 2 doesn't constrain
         * the amount of water: it just reduces the amount between peaks 1 and 3.
         *
         * General idea:
         *
         *                #
         *                #
         *          #     # #
         *          ## #  ### #
         *         ### ########  #
         *        ################
         *    idx 0123456789abcdef
         * height 1243132263423112
         *    L-R 1244444466666666
         *    R-L 6666666664433222
         *
         * where:
         * 1. idx is the index of the position
         * 2. height is the height at the position (and the input)
         *
         * We proceed in two steps:
         * 1. Moving from the left side of the array to the highest peak.
         * 2. Moving from the right side of the array to the highest peak.
         *
         * We detect a maximal peak when the peak size increases, in which case, we fill water between
         * the previous peak size and the current peak size.
         *
         * In our algorithm, peaks at indices 0, 1, 2, 8, a, c, f are the maximal peaks that would be identified.
         * Now, 0 and 1 are not truly maximal peaks since they are adjacent.
         * We could filter them out, but there is really no need since the algorithm will compute 0 water for them
         * and this would add unneeded complexity.
         *
         * The algorithm runs in time O(n) despite the nested loops:
         * 1. O(n) to find the max peak.
         * 2. O(n) to traverse from the left to the max peak, and we add water up to once for each of these nodes,
         *    so another O(n).
         * 3. Same complexity from the right to the max peak.
         * As no other array or structure is needed, the memory required is O(1).
         */

        // Elevation charts of length 0 or 1 cannot collect water.
        if constexpr (N <= 2)
            return 0;

        // Accumulated water.
        size_t water = 0;

        // Find the idx of the max peak.
        // We could do this with std::max_element and std::distance as well, but I don't see an advantage.
        size_t max_peak_idx = 0;
        for (size_t i = 1; i < N; ++i)
            if (elevation[i] > elevation[max_peak_idx])
                max_peak_idx = i;

        // Find the left peaks and calculate the water.
        size_t prev_left_max_peak_idx = 0;
        for (size_t i = 1; i <= max_peak_idx; ++i) {
            // If this is a maximal peak, add the water.
            if (elevation[i] >= elevation[prev_left_max_peak_idx]) {
                const auto water_level = elevation[prev_left_max_peak_idx];
                for (size_t j = prev_left_max_peak_idx + 1; j < i; ++j)
                    water += water_level - elevation[j];
                prev_left_max_peak_idx = i;
            }
        }

        // Find the right peaks.
        // MUST BE CAREFUL OF UNDERFLOWS DUE TO USING UNSIGNED INTEGRAL TYPEES.
        size_t prev_right_max_peak_idx = N - 1;
        for (size_t i = N - 2;
             i >= max_peak_idx && i <= N - 2 /*underflow prevention*/;
             --i) {
                // If this is a minimal peak, then add water.
                if (elevation[i] >= elevation[prev_right_max_peak_idx]) {
                    const auto water_level = elevation[prev_right_max_peak_idx];
                    // Underflow again.
                    for (size_t j = prev_right_max_peak_idx - 1;
                         j > i && j <= prev_right_max_peak_idx /*underflow prevention*/;
                         --j)
                            water += water_level - elevation[j];
                    prev_right_max_peak_idx = i;
                }
        }

        return water;
    }
}
