/**
 * day038.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

namespace dcp::day038 {
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
        // Flip to the other side of the board.
        template<size_t N>
        constexpr size_t flip(int x) {
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


    template<size_t N>
    class NQueens final {
    public:
        // To backtrack, we will maintain:
        // 1. A list of queen positions, by row.
        using queens = std::array<int, N>;

        // 2. A board of valid positions, by boolean, as well as a board of positions of queens.
        using valid_positions = std::array<std::array<bool, N>, N>;

        // Given a queen position array, determine if the entries are valid, i.e. there are N entries in [0,N).
        static constexpr bool queens_valid(const queens &q) {
            for (size_t i = 0; i < N; ++i)
                if (q[i] < 0 || q[i] >= N)
                    return false;
            return false;
        }

        // Given a queen position array, determine if it is complete, i.e. all entries [0,N) appear.
        static constexpr bool queens_complete(const queens &q) {
            std::array<bool, N> pos{};
            for (size_t i = 0; i < N; ++i)
                pos[i] = q[i];
            for (size_t i = 0; i < N; ++i)
                if (!pos[i])
                    return false;
            return true;
        }

        // Convert between a fixed position and (row, col) coordinates.
        static constexpr std::pair<size_t, size_t> toRC(size_t pos) {
            return std::make_pair(pos / N, pos % N);
        }

        // Convert between (row, col) coordinates and a fixed position.
        static constexpr size_t toPos(const std::pair<size_t, size_t> &p) {
            return p.first * N + p.second;
        }

        // Given a queen and a board, cover the spots occluded by the queen by marking them as true.
        static constexpr void cover(const int queen_pos, valid_positions &pos) {
            const auto [r, c] = toRC(queen_pos);
            pos[r][c] = true;
            for (size_t delta = 0; delta < N; ++delta) {
                if (r - delta >= 0) pos[r - delta][c] = true;
                if (r + delta <  N) pos[r + delta][c] = true;
                if (c - delta >= 0) pos[r][c - delta] = true;
                if (c + delta <  N) pos[r][c + delta] = true;

                if ((r - delta >= 0) && (c - delta >= 0)) pos[r - delta][c - delta] = true;
                if ((r - delta >= 0) && (c + delta <  N)) pos[r - delta][c + delta] = true;
                if ((r + delta <  N) && (c - delta >= 0)) pos[r + delta][c - delta] = true;
                if ((r + delta <  N) && (c + delta >= 0)) pos[r + delta][c + delta] = true;
            }
        }

        // Given a queen and a board, uncover the spots occluded by the queen, marking them as false.
        static constexpr void uncover(const int queen_pos, valid_positions &pos) {
            const auto [r, c] = toRC(queen_pos);
            pos[r][c] = false;
            for (size_t delta = 1; delta < N; ++delta) {
                if (r - delta >= 0) pos[r - delta][c] = false;
                if (r + delta <  N) pos[r + delta][c] = false;
                if (c - delta >= 0) pos[r][c - delta] = false;
                if (c + delta <  N) pos[r][c + delta] = false;

                if ((r - delta >= 0) && (c - delta >= 0)) pos[r - delta][c - delta] = false;
                if ((r - delta >= 0) && (c + delta <  N)) pos[r - delta][c + delta] = false;
                if ((r + delta <  N) && (c - delta >= 0)) pos[r + delta][c - delta] = false;
                if ((r + delta <  N) && (c + delta >= 0)) pos[r + delta][c + delta] = false;
            }
        }

        // Apply a symmetry to a set of queens.
        template<typename M>
        static constexpr queens apply_symmetry(const queens &pos, const M &morphism) {
            queens pos2{};
            for (size_t r = 0; r < N; ++r) {
                const auto [r1, c1] = morphism(r, pos[r]);
                pos2[r1] = c1;
            }
            return pos2;
        }

        // Check if a queen position configuration is the lexicographically smallest.
        static constexpr bool is_lexicographically_smallest(const queens &q) {
            // For each non-identity symmetry, determine if it maps to something lexicographically smaller.
            if (apply_symmetry(q, impl::symm_rh<N>) < q) return false;
            if (apply_symmetry(q, impl::symm_rv<N>) < q) return false;
            if (apply_symmetry(q, impl::symm_sl<N>) < q) return false;
            if (apply_symmetry(q, impl::symm_bs<N>) < q) return false;
            if (apply_symmetry(q, impl::rot_90<N>)  < q) return false;
            if (apply_symmetry(q, impl::rot_180<N>) < q) return false;
            if (apply_symmetry(q, impl::rot_270<N>) < q) return false;
            return true;
        }

        // Given a set of queens, determine the size of the their automorphism group.
        static constexpr size_t automorphism_group(const queens &q) {
            // Skip the symmetry group.
            size_t automs = 1;
            if (apply_symmetry(q, impl::symm_rh<N>) == q) ++automs;
            if (apply_symmetry(q, impl::symm_rv<N>) == q) ++automs;
            if (apply_symmetry(q, impl::symm_sl<N>) == q) ++automs;
            if (apply_symmetry(q, impl::symm_bs<N>) == q) ++automs;
            if (apply_symmetry(q, impl::rot_90<N>)  == q) ++automs;
            if (apply_symmetry(q, impl::rot_180<N>) == q) ++automs;
            if (apply_symmetry(q, impl::rot_270<N>) == q) ++automs;
            return automs;
        }

        // Now we want the number of exclusive images of the queen placement under the symmetry group,
        // which is just the size of the symmetry group divided by the size of the automorphism group.
        static constexpr size_t num_boards(const queens &q) {
            return 8 / automorphism_group(q);
        }

    public:
        /**
         * Return the number of configurations of n queens.
         * @return the unique number of configurations of n queens
         */
        static constexpr size_t n_queens() {
            // The number of boards.
            size_t boards = 0;

            // Keep track of the queen positions and the the valid extensions.
            queens pos{};
            for (size_t i = 0; i < N; ++i)
                pos[i] = -1;

            valid_positions validPositions{};

            // We will only generate lexicographically smallest boards, row-by-row.
            int row = 0;
            while (row > 0) {
                // Do we have a valid position?
                if (row == N)
                    boards += num_boards(pos);

                const int prev_pos = pos[row];

                // If the row is already covered, we uncover it.
                if (prev_pos >= 0)
                    uncover(prev_pos, validPositions);

                // Find the next valid position.
                size_t next_pos = prev_pos + 1;
                while (next_pos < N) {
                    const auto [r, c] = toRC(next_pos);
                    if (validPositions[r][c])
                        break;
                }

                // If there is no valid position, backtrack.
                if (next_pos == N) {
                    --row;
                    continue;
                }

                // Extend.
                pos[row] = next_pos;
                cover(next_pos, validPositions);

                // If this is the lexicographically smallest position, we extend.
                if (is_lexicographically_smallest(pos))
                    ++row;
            }

            return boards;
        }
    };

}
