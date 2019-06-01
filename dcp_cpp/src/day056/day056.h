/**
 * day056.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <array>

namespace dcp::day056 {
    template<int N>
    using adjacency_matrix = std::array<std::array<bool, N>, N>;

    template<int N, int K>
    constexpr std::array<int, N> colour(const adjacency_matrix<N> &adj) {
        std::array<int, N> assignment{};
        for (auto &a: assignment)
            a = -1;

        // Now we assign colour 0 to the first vertex, as it is the same as any other up to isomorphism.
        assignment[0] = 0;

        // We stop when:
        // 1. We reach back to the first vertex that we coloured 0; or
        // 2. We have a complete colouring.
        int v = 1;
        while (v >= 1 && v < N) {
            // Try the next colour for this vertex. If it is illegal, backtrack.
            ++assignment[v];
            if (assignment[v] >= K) {
                assignment[v] = -1;
                --v;
                continue;
            }

            // Check if this is a valid colour. If not, continue on to the next colour.
            bool valid = true;
            for (int u = 0; u < N; ++u)
                // If a vertex is adjacent and has the same colour, we cannot use this colour.
                if (adj[v][u] && assignment[u] == assignment[v]) {
                    valid = false;
                    break;
                }
            if (!valid)
                continue;

            // We can colour the vertex this colour, so proceed.
            ++v;
        }

        // If we failed to colour, mark the first vertex as -1.
        if (v == 0)
            assignment[0] = -1;

        // We either have a proper colouring, or an array full of -1s, which indicates failure.
        return assignment;
    }
}

/**
#pragma once

#include <array>

namespace dcp::day056 {
    template<size_t N>
    using adjacency_matrix = std::array<std::array<bool, N>, N>;

    template<size_t N, size_t K>
    constexpr std::array<int, N> colour(const adjacency_matrix<N> &adj) {
        // Colour candidates and assignments for each node for each step.
        // We need to take care of steps because if we, say, colour vertex 0 0 and this blocks 1, and then colour
        // vertex 2 0 and this also blocs 1, we don't want chaning vertex 2 to unblock colour 0 from vertex 1.
        // Fill isn't constexpr in C++17 so use for loops.
        std::array<std::array<std::array<bool, K>, N>, N> candidates{true};
        for (auto &t: candidates)
            for (auto &r: candidates)
                for (auto &c: r)
                    c = true;

        std::array<int, N> assignment{};
        for (auto &a: assignment)
            a = -1;

        // Now we assign colour 0 to the first vertex, as it is the same as any other up to isomorphism.
        assignment[0] = 0;
        for (auto &t: candidates)
            for (int v = 0; v < N; ++v)
                if (adj[0][v])
                    t[v][0] = false;

        for (int v = 1; v >= 1;) {
            // If there was an assignment for this vertex, return all the candidates.
            if (assignment[v] >= 0) {
                for (int i = 0; i < N; ++i)
                    if ()
            }
        }

        return assignment;
    }
}
*/