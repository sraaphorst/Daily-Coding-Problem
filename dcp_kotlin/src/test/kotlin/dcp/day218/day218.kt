package dcp.day218
// day218.kt
// By Sebastian Raaphorst, 2019.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import java.lang.IllegalArgumentException

class GraphGen: Gen<ReversibleDirectedGraph<Int>> {
    override fun constants() =
        listOf(
            ReversibleDirectedGraph(emptySet(), emptySet()),
            ReversibleDirectedGraph(setOf(0), setOf(0 to 0)),
            ReversibleDirectedGraph(setOf(0, 1), emptySet()),
            ReversibleDirectedGraph(setOf(0, 1), setOf(0 to 1))
        )

    override fun random(): Sequence<ReversibleDirectedGraph<Int>> = generateSequence {
        val vertexGen = Gen.choose(0, 20)
        val vertices = Gen.set(vertexGen).random().first()
        val vertexList = vertices.toList()
        val edgeGen = Gen.set(Gen.pair(Gen.from(vertexList), Gen.from(vertexList))).random()

        // On rare occasions, this throws an exception with origin and bound, probably due
        // to the number of sets. In those cases, we use an emptySet instead as a placeholder.
        val edges = try { edgeGen.first() } catch (e: IllegalArgumentException) { null }
        ReversibleDirectedGraph(vertices, edges?: emptySet())
    }
}

class ReversibleDirectedGraphTest: StringSpec() {
    init {
        "reversibleDirectedGraphTest" {
            forAll(GraphGen()) { g -> g.reverse().reverse() == g }
        }
    }
}
