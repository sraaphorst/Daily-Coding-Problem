/**
 * day085.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

int32_t selector(int32_t x, int32_t y, int32_t b) {
    // b is either 0 or 1. We want b to be a full bitmask, so if 1, 0xFFFF FFFF, and if 0, 0x0000 0000.
    // Exploit the 2s complement for this:
    b = -b;
    return (b & x) | (~b & y);
}
