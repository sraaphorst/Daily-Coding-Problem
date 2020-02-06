/**
 * day304.h
 * By Sebastian Raaphorst, 2020.
 */

#pragma once

#include <array>
using namespace std;

namespace dcp::day304 {
    using matrix = array<array<double, 11>, 11>;
    namespace {
        using matrix = array<array<double, 11>, 11>;

        /**
         * We will use this to determine a knight's possible moves in the dynamic
         * programming method.
         */
        const array<int, 8> dx {-2, -2, -1, -1,  1,  1,  2,  2};
        const array<int, 8> dy {-1,  1, -2,  2, -2,  2, -1,  1};

        /**
         * We will use this to map from starting position to isomorphism class in
         * the Markov chain method.
         */
        const array<array<int, 4>, 4> isomorphism_classes{{
            {0, 1, 3, 4},
            {1, 2, 5, 6},
            {3, 5, 7, 8},
            {4, 6, 8, 9}
        }};

        constexpr matrix operator*(const matrix &m1, const matrix &m2) noexcept {
            matrix m3{};
            for (auto r = 0; r < 11; ++r)
                for (auto c = 0; c < 11; ++c)
                    for (auto i = 0; i < 11; ++i)
                        m3[r][c] += m1[r][i] * m2[i][c];
            return m3;
        }

        constexpr matrix operator*(double scalar, const matrix &m) noexcept {
            matrix ms{};
            for (auto r = 0; r < 11; ++r)
                for (auto c = 0; c < 11; ++c)
                    ms[r][c] = scalar * m[r][c];
            return ms;
        }
    }

    /**
     * Dynamic programming approach.
     */
    constexpr double knight_survival_probability_dp(const int startx, const int starty, const int k) noexcept {
        if (startx < 0 || startx > 7 || starty < 0 || starty > 7)
            return 0;
        if (k == 0)
            return 1;

        // We want two arrays of size 8 x 8. The first will represent the step k-1, and the second will represent
        // step k.
        array<array<double, 8>, 8> prob_kprev{};
        array<array<double, 8>, 8> prob_kcur{};

        // Initially set the probability of x, y to 1 at step 0, and all others to 0 as the probability of having moved
        // to position (startx, starty) is guaranteed by nature of starting there.
        for (auto x = 0; x < 8; ++x)
            for (auto y = 0; y < 8; ++y)
                prob_kcur[x][y] = (x == startx && y == starty) ? 1 : 0;

        // Iterate over the number of steps.
        for (auto s = 0; s < k; ++s) {
            prob_kprev = prob_kcur;

            // Reset prob_kcur.
            for (auto x = 0; x < 8; ++x)
                for (auto y = 0; y < 8; ++y)
                    prob_kcur[x][y] = 0;

            // For every position, determine the probability that we can reach it.
            for (auto x = 0; x < 8; ++x) {
                for (auto y = 0; y < 8; ++y) {
                    // For every position we can reach from (i,j):
                    for (auto d = 0; d < 8; ++d) {
                        auto nx = x + dx[d];
                        auto ny = y + dy[d];

                        if (0 <= nx && nx < 8 && 0 <= ny && ny < 8)
                            prob_kcur[nx][ny] += prob_kprev[x][y] / 8;
                    }
                }
            }
        }

        // Now sum all the probabilities;
        double probability = 0;
        for (auto x = 0; x < 8; ++x)
            for (auto y = 0; y < 8; ++y)
                probability += prob_kcur[x][y];
        return probability;
    }

    /**
     * Markov chain approach.
     */
    constexpr double knight_survival_probability_markov(const int startx, const int starty, const int k) {
        if (startx < 0 || startx > 7 || starty < 0 || starty > 7)
            return 0;
        if (k == 0)
            return 1;

        /**
         * The space breaks down into 10 isomorphism classes: 0 through 9 on the board, and 10 being off the board.
         * 0 1 3 4 4 3 1 0
         * 1 2 5 6 6 5 2 1
         * 3 5 7 8 8 7 5 3
         * 4 6 8 9 9 8 6 4
         * 4 6 8 9 9 8 6 4
         * 3 5 7 8 8 7 5 3
         * 1 2 5 6 6 5 2 1
         * 0 1 3 4 4 3 1 0
         *
         * Determine the isomorphism class our starting position is in.
         * We only need the upper left quadrant of the board for this: we can map to the upper left quadrant
         * from the other three quadrants by mapping 7 to 0, 6 to 1, 5 to 2, and 4 to 3 via the function 7 % x.
         */
        const int isomorphism_class = isomorphism_classes[startx < 4 ? startx : 7 % startx][starty < 4 ? starty : 7 % starty];

        /** First we must calculate the probability of getting from any isomorphism class to any other.
         * Each space has 8 moves, and we enumerate the isomorphism classes we can move to and their frequency to
         * determine the probability. Each has 8 transitions and we assume all non-listed are 10.
         * 0: 5, 5
         * 1: 3, 6, 7
         * 2: 4, 4, 8, 8
         * 3: 1, 5, 6, 8
         * 4: 2, 5, 7, 8
         * 5: 0, 3, 4, 6, 8, 9
         * 6: 1, 3, 5, 7, 8, 9
         * 7: 1, 1, 4, 4, 6, 6, 9, 9
         * 8: 2, 3, 4, 5, 6, 8, 8, 9
         * 9: 5, 5, 6, 6, 7, 7, 8, 8
         */
        const array<array<double, 11>, 11> m = 0.125 * matrix{{
            //  0  1  2  3  4  5  6  7  8  9 10
            {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 6},
            {0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 5},
            {0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 4},
            {0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 4},
            {0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 4},
            {1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 2},
            {0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 2},
            {0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 2, 1, 0},
            {0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8}
        }};

        auto mp = m;
        for (auto i = 1; i < k; ++i)
            mp = mp * m;

        // Now the matrix mp[i][j] represents the probability of being in state j after starting in state i and making k moves.
        // Thus, the rows continue to sum to 1, so we can just take 1 - the last position in the row in which we started.
        return 1.0 - mp[isomorphism_class][10];
    }
}