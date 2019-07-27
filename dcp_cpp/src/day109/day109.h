/**
 * day109.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <bitset>

namespace dcp::day109 {
    // For an 8-bit integer:
    //   a7 a6 a5 a4 a3 a2 a1 a0
    // Return the 8-bit integer with the digits swapped:
    //   a6 a7 a4 a5 a2 a3 a0 a1
    uint8_t swap_bits(uint8_t a) {
        // Idea:
        // 1. Select the odd indexed bits, and shift them right.
        // 2. Select the even indexed bits, and shift them left.
        // 3, Combine the two using bitwise or.
        return ((a & 0b10101010u) >> 1u) | ((a & 0b01010101u) << 1u);
    }

    // Here is a brute force approach for testing.
    uint8_t swap_bits_brute_force(uint8_t a) {
        std::bitset<8> aset{a};
        for (size_t i = 0; i < 4; ++i) {
            size_t tmp = aset[2 * i];
            aset[2 * i] = aset[2 * i + 1];
            aset[2 * i + 1] = tmp;
        }

        return static_cast<uint8_t>(aset.to_ulong());
    }
}
