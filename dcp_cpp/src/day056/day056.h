d/**
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
