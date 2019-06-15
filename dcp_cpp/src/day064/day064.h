/**
 * day064.hpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <array>
#include <utility>
#include <unordered_map>

#include "day064_kd.h"

namespace dcp::day064 {
    template<size_t N>
    class KnightsTour {
        // We need to visit every square once; however, we start in a square.
        static constexpr auto REQUIRED_MOVES = N * N - 1;

        // An N x N board.
        // The board. Entry (i,j) indictes the last known step to get there. So:
        // board[][j] = 0 is the starting position;
        // board[i[j] = n means at the end of the nth turn, the knight moved there; and
        // board[i]j] = -1 is a free position.
        using game_board = std::array<std::array<int, N>, N>;


        // A struct of information stored during a knight move.
        // This is the information that records us moving INTO this position.
        struct KnightInfo {
            // The row and the column of
            const int row = -1;
            const int col = -1;

            // The move that was last made here. This is an index into all_knights_directions.
            int move_out_idx = 0;
        };

        // A list of the movements made by the knight.
        using knight_moves = std::array<KnightInfo, N * N>;

        // We need the following information:
        // 1. The board and its coverage
        // 2. The list of movements of the knight
        // 3. The total number of boards found so far.



        /**
         * An auxiliary method to calculate the number of knight's tours.
         * Given a row position and a column position, determine the number of tours in the board beginning at that
         * row position and that column position.
         * @param r row position
         * @param c column position
         * @return number of completed boards that can be reached starting from that position
         */
        static constexpr size_t calculate_aux(const int r0, const int c0) noexcept {
            // The number of knight boards we have found.
            size_t num_boards = 0;


            // *** Create the array representing the movement of the knight.
            // *** The top most entry represents the current position and thus, we start with one, i.e. entrance.
            knight_moves moves{};
            for (size_t i = 0; i < N * N; ++i)
                moves[i] = KnightDirections::NONE;
            moves[0] = KnightInfo{r0, c0, KnightDirections::NONE};

            // Mark the board as being empty.
            game_board board{};
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j)
                    board[i][j] = 0;

            // The original placement.
            board[r0][c0] = 0;

            // Continue until we must backtrack off the board.
            // We start in position 0 and continue until
            for (int current_move_num = 0; current_move_num >= 0; ) {

                // ***** 1 *****
                // Do we have a complete board?
                // If we do, record and then backtrack to the next possible move.
                if (current_move_num == REQUIRED_MOVES) {
                    ++num_boards;
                    --current_move_num;
                    continue;
                }


                // ***** 2 *****
                // Did we backtrack to this position?
                // Check to see if the row is already covered.
                if (moves[current_move_num].move_out != KnightDirections::NONE) {
                    // Continuously try the next position until we run out of positions.

                }
            }



        }

    public:
        static constexpr size_t calculate() noexcept  {
            // We improve here by only processing part of the board.
            // In the case of an odd number, we have extra work to do, so we delegate to a helper function above.
        }
    };
}