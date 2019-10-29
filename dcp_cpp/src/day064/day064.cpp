/**
 * knights tour.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#include <iostream>
#include <array>
#include <utility>
#include <cassert>

namespace dcp::day064 {
    template<int N>
    class KnightsTour final {
        // The types of moves that a knight can make:
        enum KnightMoves {
            NONE = 0,
            NNE,
            NEE,
            SEE,
            SSE,
            SSW,
            SWW,
            NWW,
            NNW,
            DONE,
        };

        static constexpr std::pair<int, int> move(int row, int col, int move_type) {
            switch (move_type) {
                case KnightMoves::NONE:
                    return {row, col};
                case KnightMoves::NNE:
                    return {row - 2, col + 1};
                case KnightMoves::NEE:
                    return {row - 1, col + 2};
                case KnightMoves::SEE:
                    return {row + 1, col + 2};
                case KnightMoves::SSE:
                    return {row + 2, col + 1};
                case KnightMoves::SWW:
                    return {row + 1, col - 2};
                case KnightMoves::SSW:
                    return {row + 2, col - 1};
                case KnightMoves::NWW:
                    return {row - 1, col - 2};
                case KnightMoves::NNW:
                    return {row - 2, col - 1};
                default:
                    return {-1, -1};
            }
        }

        // We create a stack of moves.
        struct KnightPos {
            int row;
            int col;
            int last_move;
        };

        using KnightStack = std::array<KnightPos, N * N>;

        // And we have a board indicating cell usage.
        // Position (r,c) is:
        // 1. -1 if not visited.
        // 2. the move number if visited.
        using ChessBoard = std::array<std::array<int, N>, N>;

    public:
        /**
         * Start searching for a knight's tour from row r0 and column c0.
         * @param r0 the row
         * @param c0 the column
         * @return the number of tours
         */
        static constexpr int computeFrom(int r0, int c0) noexcept {
            int num_boards = 0;

            KnightStack stack{};
            for (int i = 0; i < N * N; ++i) {
                stack[i].row = -1;
                stack[i].col = -1;
                stack[i].last_move = NONE;
            }
            stack[0].row = r0;
            stack[0].col = c0;

            ChessBoard board{};
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j)
                    board[i][j] = -1;
            board[r0][c0] = 0;


            // Now perform the backtracking algorithm.
            for (int move_index = 0; move_index >= 0;) {
                // Check if we have a complete game.
                // If so, we have to backtrack.
                if (move_index == N * N - 1) {
                    ++num_boards;

                    KnightPos &pos = stack[move_index];
                    board[pos.row][pos.col] = -1;
                    pos.row = -1;
                    pos.col = -1;
                    pos.last_move = KnightMoves::NONE;
                    --move_index;
                    continue;
                }

                // Now we try to advance.
                KnightPos &pos = stack[move_index];
                for (++pos.last_move; pos.last_move != KnightMoves::DONE; ++pos.last_move) {
                    // Get the next position if we took this move.
                    const auto next_pos = move(pos.row, pos.col, pos.last_move);
                    const auto next_r = next_pos.first;
                    const auto next_c = next_pos.second;

                    // If we can extend, extend.
                    if (next_r >= 0 && next_r < N && next_c >= 0 && next_c < N && board[next_r][next_c] == -1) {
                        ++move_index;
                        KnightPos &nextpos = stack[move_index];
                        nextpos.row = next_r;
                        nextpos.col = next_c;
                        nextpos.last_move = KnightMoves::NONE;
                        board[next_r][next_c] = move_index;
                        break;
                    }
                }

                // Now if we could not advance, backtrack.
                // Clear out the board and the stack.
                if (pos.last_move >= KnightMoves::DONE) {
                    board[pos.row][pos.col] = -1;
                    pos.row = -1;
                    pos.col = -1;
                    pos.last_move = KnightMoves::NONE;
                    --move_index;
                    continue;
                }
            }

            return num_boards;
        }

    public:
        /**
         * Search the entire board for knight's tours.
         * Note that this will give many, many isomorphic copies.
         * @return the total number of knights' tours
         */
        static constexpr int compute() {
            int num_boards = 0;
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j)
                    num_boards += computeFrom(i, j);
            return num_boards;
        }
    };
}

int main() {
    auto boards00 = dcp::day064::KnightsTour<5>::computeFrom(0, 0);
    assert(boards00 == 304);
    auto boards01 = dcp::day064::KnightsTour<5>::computeFrom(0, 1);
    assert(boards01 == 0);
    auto boards02 = dcp::day064::KnightsTour<5>::computeFrom(0, 2);
    assert(boards02 == 56);
    auto boards11 = dcp::day064::KnightsTour<5>::computeFrom(1, 1);
    assert(boards11 == 56);
    auto boards21 = dcp::day064::KnightsTour<5>::computeFrom(2, 1);
    assert(boards21 == 0);
    auto boards22 = dcp::day064::KnightsTour<5>::computeFrom(2, 2);
    assert(boards22 == 64);
    std::cout << "All tests passed.\n";
}