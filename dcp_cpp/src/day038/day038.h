/**
 * day038.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <array>

namespace dcp::day038 {
    template<int N>
    class NQueens final {
        // To backtrack, we will maintain:
        // 1. A list of queen column positions, by row. -1 indicates out-of-play.
        using queens = std::array<int, N>;

        // 2. A board of valid positions. The idea here is that a position is marked by the row of the queen
        // that made it invalid. All positions start off as -1, indicating that they are free.
        using valid_positions = std::array<std::array<int, N>, N>;

        // Convert between a fixed position and (row, col) coordinates.
        static constexpr std::pair<int, int> toRC(int pos) {
            return std::make_pair(pos / N, pos % N);
        }

        // Convert between (row, col) coordinates and a fixed position.
        static constexpr size_t toPos(const std::pair<int, int> &p) {
            return p.first * N + p.second;
        }


        /**
         * Convenience method to perform both covering and uncovering.
         * Covered cells have the ID (by row) of the queen who first covered them.
         * Uncovered cells have the value -1.
         * @row the row to begin
         * @col the column to begin
         * @param to_change the value to change
         * @param what to change it to
         * @param valid_pos the array of valid positions
         */
        static constexpr void cover_uncover_aux(const int row,
                                                const int col,
                                                const int to_change,
                                                const int change_to,
                                                valid_positions &valid_pos) {
            // Left-to-right
            for (int delta = 0; delta < N; ++delta) {
                const auto cidx = (col + delta) % N;
                if (valid_pos[row][cidx] == to_change)
                    valid_pos[row][cidx] = change_to;
            }

            // Top-to-bottom.
            for (int delta = 0; delta < N; ++delta) {
                const auto ridx = (row + delta) % N;
                if (valid_pos[ridx][col] == to_change)
                    valid_pos[ridx][col] = change_to;
            }

            // The diagonals are more complex as they may not span a whole N entries.
            // Upper left and Lower right.
            for (int delta = -N; delta < N; ++delta) {
                const auto ridx = row + delta;
                if (ridx < 0 or ridx >= N) continue;
                const auto cidx = col + delta;
                if (cidx < 0 or cidx >= N) continue;
                if (valid_pos[ridx][cidx] == to_change)
                    valid_pos[ridx][cidx] = change_to;

            }

            // Upper right to lower left.
            for (int delta = -N; delta < N; ++delta) {
                const auto ridx = row + delta;
                if (ridx < 0 or ridx >= N) continue;
                const auto cidx = col - delta;
                if (cidx < 0 or cidx >= N) continue;
                if (valid_pos[ridx][cidx] == to_change)
                    valid_pos[ridx][cidx] = change_to;

            }
        }

        static constexpr void cover(const int row, const int col, valid_positions &valid_pos) noexcept {
            cover_uncover_aux(row, col, -1, row, valid_pos);
        }

        static constexpr void uncover(const int row, const int col, valid_positions &valid_pos) noexcept  {
            cover_uncover_aux(row, col, row, -1, valid_pos);
        }

    public:
        /**
         * Return the number of configurations of n queens.
         * @return the unique number of configurations of n queens
         */
        static constexpr size_t calculate() {
            if (N == 0)
                return 0;

            // The number of boards.
            size_t num_boards = 0;

            // Keep track of the queen positions and the the valid extensions.
            queens queen_positions{};
            for (size_t i = 0; i < N; ++i)
                queen_positions[i] = -1;

            // All valid positions should initially be free, i.e. -1.
            valid_positions valid_positions{};
            for (size_t i = 0; i < N; ++i)
                for (size_t j = 0; j < N; ++j)
                    valid_positions[i][j] = -1;

            for (int row = 0; row >= 0;) {
                // Do we have a valid, complete board?
                if (row == N) {
                    ++num_boards;

                    // Backtrack to the previous row and continue.
                    --row;
                }

                // If the row is already covered, i.e. we backtracked to it, remove the queen, uncover what it was
                // occluding, and then try to find a next position in the row in which to place her.
                const auto previous_position = queen_positions[row];
                if (previous_position >= 0) {
                    uncover(row, previous_position, valid_positions);
                }

                // Find the next valid position.
                auto next_position = previous_position + 1;
                while (next_position < N) {
                    if (valid_positions[row][next_position] == -1)
                        break;
                    ++next_position;
                }

                // If we could not find a next position, we have to backtrack.
                if (next_position == N) {
                    queen_positions[row] = -1;
                    --row;
                    continue;
                }

                // Otherwise, we found a next position for the queen. Cover it and extend.
                queen_positions[row] = next_position;
                cover(row, next_position, valid_positions);
                ++row;
            }

            return num_boards;
        }
    };

}
