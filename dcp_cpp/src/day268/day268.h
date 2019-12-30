/**
 * day268.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

namespace dcp::day268 {

    /**
     * An O(1) algorithm to determine if a non-negative (unsigned) 32 bit integer is a power of 4.
     * @param n the number to check
     * @return true if n is a poer of 4, and false otherwise.
     */
    [[nodiscard]] constexpr bool isPowerOf4(uint32_t n) noexcept {
        /**
         * Steps:
         * 1. n != 0
         *     0 is not a power of 4.
         *
         * 2. n & 0x55555555 == n
         *     0x55555555 = 4^0 + ... + 4^15, and 4^15 is the largest power of 4 that can fit in 32 bits.
         *     Thus, when we & with this, we are filtering out the powers of 4 and making sure that only they remain,
         *     so the number is a sum of powers of 4.
         *
         * 3. n & (n-1) == 0
         *     Ensures that only one bit is set. Example: 0x100000 - 1 = 0x011111, and 0x100000 & 0x011111 == 0.
         *     If any other bit was set, only the lowest order bit and the bits beneath it would be affected, and all
         *     higher order bits would remain the same, so the & would result in the higher order bits.
         */
         return n != 0
                && (n & 0x55555555u) == n
                && ((n & (n-1)) == 0);
    }
}