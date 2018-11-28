package codesample.kotlin.sandbox.algorithms.graphs

import java.util.*

class Graph(val size: Int) {
    private val adj: List<MutableList<Int>> = List(size) { mutableListOf<Int>() }

    fun addEdge(one: Int, two: Int): Unit {
        adj[one].add(two)
        adj[two].add(one)
    }

    fun adj(verIndex: Int): MutableList<Int> = adj[verIndex]

    override fun toString(): String {
        return "size: $size vertexes: \n" +
                adj.asSequence()
                        .mapIndexed { index, mutableList -> "$index: $mutableList" }
                        .joinToString("\n")
    }
}

class DFSPaths(private val graph: Graph, private val initialVertex: Int) {
    private val marked: Array<Boolean> = Array(graph.size) { false }
    private val edgeTo: Array<Int> = Array(graph.size) { 999 }

    init {
        dfs(graph, initialVertex)
    }

    private fun dfs(graph: Graph, curVertex: Int) {
        marked[curVertex] = true
        for (adjV in graph.adj(curVertex))
            if (!marked[adjV]) {
                dfs(graph, adjV)
                edgeTo[adjV] = curVertex
            }
    }

    fun hasPathTo(ver: Int) = marked[ver]

    fun pathFromInitialTo(ver: Int): List<Int> {
        return if (!hasPathTo(ver))
            emptyList()
        else {

            if (ver == initialVertex)
                return listOf(0, 0)

            val dec = ArrayDeque<Int>()

            var closestToTarget = edgeTo[ver]
            while (closestToTarget != initialVertex) {
                dec.push(closestToTarget)
                closestToTarget = edgeTo[closestToTarget]
            }
            dec.push(ver)
            dec.push(initialVertex)
            dec.toList()
        }

    }
}


fun getDefaultGraph(): Graph {
    val graph = Graph(10)
    graph.addEdge(0, 1)
    graph.addEdge(0, 3)
    graph.addEdge(0, 5)
    graph.addEdge(1, 5)
    graph.addEdge(1, 2)
    graph.addEdge(1, 4)
    graph.addEdge(4, 6)
    graph.addEdge(6, 8)
    graph.addEdge(7, 8)
    graph.addEdge(8, 9)
    graph.addEdge(2, 3)
    graph.addEdge(2, 8)
    graph.addEdge(2, 9)
    return graph
}

fun main(args: Array<String>) {
    val graph = getDefaultGraph()

    println(graph)
    var dfs = DFSPaths(graph, 0)
    println(dfs.hasPathTo(1))
    print(dfs.pathFromInitialTo(1))
}
