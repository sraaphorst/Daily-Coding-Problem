package dcp.day303

import kotlin.math.abs
import kotlin.math.min

fun angle(hh: Int, mm: Int): Int {
    require(hh >= 0 && hh <= 23)
    require(mm >= 0 && mm <= 59)

    // Each hour is 5 degrees but we mod 12.
    val hhangle = (((hh % 12) * 30) + (mm / 2))
    val mmangle = (mm * 6)
    return abs(min((hhangle - mmangle), 360 - hhangle + mmangle))
}

fun main() {
    for (hh in (0 until 12))
        for (mm in (0 until 60 step 15))
            println("$hh:$mm -> ${angle(hh, mm)}")
}