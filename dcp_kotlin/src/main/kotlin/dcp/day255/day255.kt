package dcp.day255
// day55.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.min

typealias AdjacencyGraph = List<List<Int>>
typealias TransitiveClosure = List<List<Int>>

// Add vectors so max values are 1: we only care about there being a path (1) or not being a path (0) between
// two vectors.
fun addVectors(v1: List<Int>, v2: List<Int>): List<Int> =
    v1.zip(v2).map { (a1, a2) -> min(1, a1 + a2 )}

// Given an adjacency graph, find its transitive closure, i.e. the square matrix M such that M[i][j] = 1
// iff v_i can reach v_j.
fun adjacencyGraphtoTransitiveClosure(adj: AdjacencyGraph): TransitiveClosure {
    val N = adj.size
    val adjMatrix = adj.map { row -> (0 until N).map{ if (it in row) 1 else 0} }
    println(adj)
    println()
    println(adjMatrix)
    println()
    // Add the rows of the adjMatrix indicated together.
    // e.g. adjMatrix row 0, 1, 3 adds rows 0, 1, and 3 of the adjMatrix. Cap out at 1. }
    //return adj.map{ row -> row.fold(List(N){0}){acc, idx -> addVectors(acc, adjMatrix[idx])} }
    return adj.withIndex().map{ (i, row) -> row.fold(adjMatrix[i]){acc, idx -> addVectors(acc, adjMatrix[idx])} }
}

fun main() {
    val ad = listOf(
        listOf(1, 2),
        listOf(2),
        listOf(0),
        listOf()
    )

    println(adjacencyGraphtoTransitiveClosure(ad))
    println()
    println()


    val adjgraph = listOf(
        listOf(0, 1, 1, 0),
        listOf(0, 0, 1, 0),
        listOf(1, 0, 0, 1),
        listOf()
    )

    val tc = listOf(
        listOf(1, 1, 1, 1),
        listOf(1, 1, 1, 1),
        listOf(1, 1, 1, 1),
        listOf(0, 0, 0, 1)
    )

    println(adjacencyGraphtoTransitiveClosure(adjgraph))
}