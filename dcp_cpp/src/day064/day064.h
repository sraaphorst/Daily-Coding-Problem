/**
 * day064.hpp
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <array>
#include <utility>
#include <unordered_map>

namespace dcp::day064 {
    template<size_t N>
    class KnightsTour {
        // The directions a knight can take.
        enum class KnightDirections {
            NONE,

            NNE,
            NEE,

            SEE,
            SSE,

            SSW,
            SWW,

            NWW,
            NNW,
        };

        // An array of all moving directions so that we can iterate over them at each square and make sure each
        // was executed.
        const std::array<8, KnightDirections> directions{
            KingsDirections::NNE,
            KingsDirections::NEE,
            KingsDirections::SEE,
            KingsDirections::SSE,
            KingsDirections::SSW,
            KingsDirections::SWW,
            KingsDirections::NWW,
            KingsDirections::NNW
        };

        // A single movemment.
        using Offset = std::pair<int, int>;

        // The movements by direction.
        using KnightMovement = std::unordered_map<KnightDirections, Offset>;

        // The deltas
        const KnightMovement knight_movement = {
                {KnightDirections::NONE, Offset{ 0,  0}},

                {KnightDirections::NNE,  Offset{-2,  1}},
                {KnightDirections::SSW,  Offset{ 2, -1}},

                {KnightDirections::NEE,  Offset{-1,  2}},
                {KnightDirections::SWW,  Offset{ 1, -2}},

                {KnightDirections::SEE,  Offset{ 1,  2}}
                {KnightDirections::NWW,  Offset{-1, -2}},

                {KnightDirections::SSE,  Offsets{ 2, 1}},
                {KnightDirections::NNW,  Offsets{-2, 1}}
        };

        // An N x N board.
        // The board. Entry (i,j) indictes the last known step to get there. So:
        // board[][j] = 0 is the starting position;
        // board[i[j] = n means at the end of the nth turn, the knight moved there; and
        // board[i]j] = -1 is a free position.
        using game_board = std::array<std::array<int, N>, N>;

        // A complete board has N * N - 1 moves to unique squares:
        // 0. The original origin.
        // N * N - 1. Moves to the remaining squares.
        constexpr auto required_moves = N * N - 1;

        // A list of the movements made by the knight.
        using knight_moves = std::array<KnightDirections, N * N - 1>;

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
            size_t num_boards = 0;

            // Initialize the movement of the night.
            knight_moves moves{};
            for (size_t i = 0; i < N * N; ++i)
                moves[i] = KnightDirections::NONE;

            // Mark the board as being empty.
            game_board board{};
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j)
                    board[i][j] = 0;

            // The original placement.
            board[r0][c0] = 0;

            // Continue until we must backtrack off the board.
            for (int current_move_num = 0; current_move_num >= 0; ) {

                // Do we have a complete board?
                // We consider a complete board to be one less
                if (current_move_num == N * N - 1) {
                    ++num_boards;
                    --c
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