package dcp.day273
// day273.kt
// By Sebastian Raaphorst, 2020.

fun List<Int>.findFixedPoint(): Int? =
    withIndex().find { it.index == it.value }?.index
