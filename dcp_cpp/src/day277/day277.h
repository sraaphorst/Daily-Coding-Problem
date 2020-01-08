/**
 * day276.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <algorithm>
#include <iterator>
#include <type_traits>

namespace dcp::day277 {
#ifndef __HEADER_EXTRACTOR
#define __HEADER_EXTRACTOR 0b11000000u
#endif
#ifndef __HEADER_RESULT
#define __HEADER_RESULT 0b10000000u
#endif

    namespace details {
        /**
         * Count the number of 1s from the left.
         * @return
         */
        constexpr int count_bits_from_left(const uint8_t byte) noexcept {
            int i = 0;
            for (int j = 7; j >= 0; --j) {
                if ((1 << j) & byte) //NOLINT
                    ++i;
                else
                    return i;
            }
            return 8;
        }

        constexpr bool starts_with_10(const uint8_t byte) noexcept {
            return (__HEADER_EXTRACTOR & byte) == __HEADER_RESULT;
        }
    }

    template<typename Iter, typename IterType = std::decay_t<typename std::iterator_traits<Iter>::value_type>>
    [[nodiscard]] constexpr
    std::enable_if_t<std::is_same_v<IterType, uint8_t>, bool> isUTF8(Iter begin, const Iter end) noexcept {
        while (begin != end) {
            // Get the first byte of the unicode character.
            uint8_t first = *begin++;

            // Determine how many bytes are in the character.
            int bytes_in_character = std::min(details::count_bits_from_left(first), 4);

            // Nothing can start with just one 1.
            if (bytes_in_character == 1)
                return false;

            // Eat up the bytes, checking for 10 starts.
            // Note we use 1 here because we have already eaten up one byte in the unicode character.
            while (bytes_in_character > 1) {
                // If there are no characters left, we have an invalid UTF-8 encoding.
                if (begin == end)
                    return false;
                if (!details::starts_with_10(*begin++))
                    return false;
                --bytes_in_character;
            }
        }

        return true;
    }
}
