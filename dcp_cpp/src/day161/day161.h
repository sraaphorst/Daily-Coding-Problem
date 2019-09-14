/**
 * day161.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

namespace dcp::day161 {
    constexpr uint32_t reverse_digits(const uint32_t bits) {
        return ~bits;
    }
}
