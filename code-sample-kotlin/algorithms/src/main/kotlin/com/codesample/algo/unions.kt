package com.codesample.algo


abstract class Union(val size: Int) {
    abstract fun union(indexFirst: Int, indexSecond: Int)
    abstract fun connected(indexFirst: Int, indexSecond: Int): Boolean
}

/** Union with fast 'find' operation*/
class QuickFindUnion(size: Int): Union(size) {
    override fun union(indexFirst: Int, indexSecond: Int) {
        TODO("Not yet implemented")
    }

    override fun connected(indexFirst: Int, indexSecond: Int): Boolean {
        TODO("Not yet implemented")
    }
}

/** Union with fast 'union' operation */
class QuickUnionUnion(size: Int): Union(size) {
    override fun union(indexFirst: Int, indexSecond: Int) {
        TODO("Not yet implemented")
    }

    override fun connected(indexFirst: Int, indexSecond: Int): Boolean {
        TODO("Not yet implemented")
    }
}

fun main() {
    val ufUnion = QuickFindUnion(10)

}
