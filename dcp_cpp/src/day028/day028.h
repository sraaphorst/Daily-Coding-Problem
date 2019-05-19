/**
 * day027.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

namespace dcp::day028 {
    /**
     * The simple approach would be to do a backtracking approach, but we can do better than this.
     * Number the rows and columns beginning with (0, 0) in the upper-left corner.
     * The symmetry group of a square of side N has size 8, namely:
     * 1. identity              (x, y) -> (        x,         y)
     * 2. horizontal reflection (x, y) -> (N - x - 1,         y)
     * 3. vertical reflection   (x. y) -> (        x,        -y)
     * 4. \ reflection          (x, y) -> (        y,         x)
     * 5. / reflection          (x, y) -> (N - y - 1, N - x - 1)
     * 6. Rotation by 90        (x, y) -> (        y, N - x - 1)
     * 7. Rotation by 180       (x, y) -> (N - x - 1, N - y - 1)
     * 8. Rotation by 270       (x, y) -> (N - y - 1,         x)
     */

    namespace impl {
        template<size_t N>
        size_t flip(int x) {
            return N - x - 1;
        }

        template<size_t N>
        constexpr auto symm_id = [] (size_t x, size_t y) { return std::make_pair(x, y); };

        template<size_t N>
        constexpr auto symm_rh = [] (size_t x, size_t y) { return std::make_pair(flip<N>(x), y); };

        template<size_t N>
        constexpr auto symm_rv = [] (size_t x, size_t y) { return std::make_pair(x, flip<N>(y)); };

        template<size_t N>
        constexpr auto symm_sl = [] (size_t x, size_t y) { return std::make_pair(flip<N>(x), flip<N>(y)); };

        template<size_t N>
        constexpr auto symm_bs = [] (size_t x, size_t y) { return std::make_pair(flip<N>(y), flip<N>(x)); };

        template<size_t N>
        constexpr auto rot_90 = [] (size_t x, size_t y) { return std::make_pair(y, flip<N>(x)); };

        template<size_t N>
        constexpr auto rot_180 = [] (size_t x, size_t y) { return std::make_pair(flip<N>(x), flip<N>(y)); };

        template<size_t N>
        constexpr auto rot_270 = [] (size_t x, size_t y) { return std::make_pair(flip<N>(y), x); };
    }

}