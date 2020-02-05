package dcp.day303
// day303.kt
// By Sebastian Raaphorst, 2002.

import kotlin.math.min
import kotlin.math.roundToInt

// Return the minimum angle to the nearest degree between the hour hand and the minute hand of a 12 hour clock at hh:mm.
fun minClockAngle(h: Int, m: Int): Int {
    require(h in 0..23)
    require(m in 0..59)

    // Reduce to 12 hour time and make everything double to get closest precision.
    val hh = (h % 12).toDouble()
    val mm = m.toDouble()

    val hourHand = hh * 30 + mm / 2
    val minuteHand = 6 * mm
    val diff1 = run {
        val d1 = hourHand - minuteHand
        if (d1 < 0) d1 + 360 else d1
    }
    val diff2 = run {
        val d2 = minuteHand - hourHand
        if (d2 < 0) d2 + 360 else d2
    }
    return min(diff1, diff2).roundToInt()
 }
