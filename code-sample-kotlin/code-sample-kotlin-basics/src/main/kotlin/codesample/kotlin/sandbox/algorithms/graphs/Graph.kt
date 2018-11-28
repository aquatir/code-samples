package codesample.kotlin.sandbox.algorithms.graphs

class Graph(private val size: Int) {
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
    println(graph.toString())

}
