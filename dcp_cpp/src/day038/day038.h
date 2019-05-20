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


        // To backtrack, we will maintain:
        // 1. A list of queen positions, by row.
        template<size_t N>
        using queens = std::array<int, N>;

        // 2. A board of valid positions, by boolean, as well as a board of positions of queens.
        template<size_t N>
        using valid_positions = std::array<std::array<bool, N>, N>;

        // Given a queen position array, determine if the entries are valid, i.e. there are N entries in [0,N).
        template<size_t N>
        constexpr bool queens_valid(const queens<N> &q) {
            for (size_t i = 0; i < N; ++i)
                if (q[i] < 0 || q[i] >= N)
                    return false;
            return false;
        }

        // Given a queen position array, determine if it is complete, i.e. all entries [0,N) appear.
        template<size_t N>
        constexpr bool queens_complete(const queens<N> &q) {
            std::array<bool, N> pos{};
            for (size_t i = 0; i < N; ++i)
                pos[i] = q[i];
            for (size_t i = 0; i < N; ++i)
                if (!pos[i])
                    return false;
            return true;
        }

        // Convert between a fixed position and (row, col) coordinates.
        template<size_t N>
        constexpr std::pair<size_t, size_t> toRC(size_t pos) {
            return std::make_pair(pos / N, pos % N);
        }

        template<size_t N>
        constexpr size_t toPos(const std::pair<size_t, size_t> &p) {
            return p.first * N + p.second;
        }

        // Given a queen and a board, cover the spots occluded by the queen by marking them as true.
        template<size_t N>
        constexpr void cover(const int queen_pos, valid_positions<N> &pos) {
            const auto [r, c] = toRC<N>(queen_pos);
            pos[r][c] = true;
            for (int delta = 0; delta < N; ++delta) {
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

        // Given a queen and a board, uncover the spots occluded by the queen, marking them as false;
        template<size_t N>
        constexpr void uncover(const int queen_pos, valid_positions<N> &pos) {
            const auto [r, c] = toRC<N>(queen_pos);
            pos[r][c] = false;
            for (int delta = 1; delta < N; ++delta) {
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
        template<size_t N, typename M>
        constexpr queens<N> apply_symmetry(const queens<N> &pos, const M &morphism) {
            queens<N> pos2{};
            for (int r = 0; r < N; ++r) {
                const auto [r1, c1] = morphism(r, pos[r]);
                pos2[r1] = c1;
            }
            return pos2;
        }

        // Check if a queen position configuration is the lexicographically smallest.
        template<size_t N>
        constexpr bool is_lexicographically_smallest(const queens<N> &q) {
            // For each non-identity symmetry, determine if it maps to something lexicographically smaller.
            if (apply_symmetry<N>(q, symm_rh<N>) < q) return false;
            if (apply_symmetry<N>(q, symm_rv<N>) < q) return false;
            if (apply_symmetry<N>(q, symm_sl<N>) < q) return false;
            if (apply_symmetry<N>(q, symm_bs<N>) < q) return false;
            if (apply_symmetry<N>(q, rot_90<N>)  < q) return false;
            if (apply_symmetry<N>(q, rot_180<N>) < q) return false;
            if (apply_symmetry<N>(q, rot_270<N>) < q) return false;
            return true;
        }

        // Given a set of queens, determine the size of the their automorphism group.
        template<size_t N>
        constexpr size_t automorphism_group(const queens<N> &q) {
            // Skip the symmetry group.
            size_t automs = 1;
            if (apply_symmetry<N>(q, symm_rh<N>) == q) ++automs;
            if (apply_symmetry<N>(q, symm_rv<N>) == q) ++automs;
            if (apply_symmetry<N>(q, symm_sl<N>) == q) ++automs;
            if (apply_symmetry<N>(q, symm_bs<N>) == q) ++automs;
            if (apply_symmetry<N>(q, rot_90<N>)  == q) ++automs;
            if (apply_symmetry<N>(q, rot_180<N>) == q) ++automs;
            if (apply_symmetry<N>(q, rot_270<N>) == q) ++automs;
            return automs;
        }

        // Now we want the number of exclusive images of the queen placement under the symmetry group,
        // which is just the size of the symmetry group divided by the size of the automorphism group.
        template<size_t N>
        constexpr size_t num_boards(const queens<N> &q) {
            return 8 / automorphism_group(q);
        }
    }

    // Now the backtracking.
    template<size_t N>
    size_t n_queens() {
        // The number of boards.
        size_t boards = 0;

        // Keep track of the queen positions and the the valid extensions.
        impl::queens<N> pos;
        for (size_t i = 0; i < N; ++i)
            pos[i] = -1;

        impl::valid_positions<N> validPositions;

        // We will only generate lexicographically smallest boards, row-by-row.
        int row = 0;
        while (row > 0) {
            // Do we have a valid position?
            if (row == N)
                boards += impl::num_boards<N>(pos);

            const int prev_pos = pos[row];

            // If the row is already covered, we uncover it.
            if (prev_pos >= 0)
                impl::uncover<N>(prev_pos, validPositions);

            // Find the next valid position.
            int next_pos = prev_pos + 1;
            while (next_pos < N) {
                const auto [r, c] = impl::toRC<N>(next_pos);
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
            impl::cover<N>(next_pos, validPositions);

            // If this is the lexicographically smallest position, we extend.
            if (impl::is_lexicographically_smallest<N>(pos))
                ++row;
        }

        return boards;
    }
}
