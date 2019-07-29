/**
 * day113.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <algorithm>
#include <string>

namespace dcp::day113 {
    void swap_words(std::string &str) {
        // First reverse the entire string.
        // This results in the words being in the correct positions, but backwards.
        const auto length  = str.length();
        const auto halfway = str.length() / 2;

        for (size_t i = 0; i < halfway; ++i)
            std::swap(str[i], str[length - i - 1]);

        // Now iterate over the words, reversing them.
        size_t start_idx = 0;
        size_t end_idx = 0;
        while (start_idx < length) {
            // A word boundary is detected at a space or the end of the string.
            if (end_idx == length || str[end_idx] == ' ') {
                const auto halfway_word = (end_idx - start_idx) / 2;
                for (size_t i = 0; i < halfway_word; ++i)
                    std::swap(str[start_idx + i], str[end_idx - i - 1]);
                start_idx = end_idx + 1;
            }
            ++end_idx;
        }
    }
}
