/**
 * KnightsDirections.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <array>
#include <optional>
#include <unordered_map>
#include <utility>

namespace dcp::day064::impl {
    using coordinates = std::pair<int, int>;

    namespace impl {
        // A single movement.
        using Offset = std::pair<int, int>;
    }

    /**
     * A direction that a chess knight can head in.
     * We provide a parameter to prevent overflowing off the edge of the square board.
     */
    class Offset {
    private:
    const int x0;
    const int y0;

    public:
        constexpr Offset() noexcept: x0{0}, y0{0} {}
        constexpr Offset(int x0, int y0): x0{x0}, y0{y0} {}
        constexpr Offset(const Offset&) = default;
        constexpr Offset(Offset&&) = default;

        constexpr std::optional<coordinates> move(int x, int y) {
            const auto x1 = x0 + x;
            const auto y1 = y0 + y;

            if (x1 < 0 or y1 < 0)
                return {};
            else
                return std::make_optional(std::make_pair(x, y));
        }
    };


    /**
     * Impose an ordering on these directions. These are how we will traverse them from a square.
     * Note that the NONE is first, because it it the placeholder the indicates that we have not yet left
     * the square.
     */
     enum class KnightsDirectionIndex {
         NONE = 0,
         NNE,
         NEE,
         SEE,
         SSE,
         SSW,
         SWW,
         NWW,
         NNW,
     };

     constexpr std::array<Offset, 9> all_knights_directions {
        Offset{},
        Offset{-2,  1},
        Offset{-1,  2},
        Offset{ 1,  2},
        Offset{ 2,  1},
        Offset{ 2, -1},
        Offset{ 1, -2},
        Offset{-1, -2},
        Offset{-2, -1}
     };
}
