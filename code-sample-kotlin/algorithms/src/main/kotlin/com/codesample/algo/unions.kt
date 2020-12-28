package com.codesample.algo


abstract class Union<T>() {
    /** Make two elements point to same union */
    abstract fun union(fst: T, snd: T)

    /** Return true if two elements are connected or false otherwise. Same elements are always considered connected */
    abstract fun connected(fst: T, snd: T): Boolean
}

fun Union<Int>.printConnected(fst: Int, snd: Int) {
    if (this.connected(fst, snd))
        println("'$fst' and '$snd' are connected") else
        println("'$fst' and '$snd' are NOT connected")
}

/** Union with fast 'connected' operation O(1) and slow 'union' O(n)
 *
 * The entries are connected if and only if they have the same index.
 * Implementation is NOT thread-safe!*/
class QuickFindUnion<T> : Union<T>() {

    /** Next created union index. Incremented each time a new element in added to this union in 'connected' call */
    private var nextUnionIndex = 0

    /** Map element to index of union */
    private val elements: MutableMap<T, Int> = mutableMapOf()

    override fun union(fst: T, snd: T) {

        // Maybe insert new element and return if two elements are the same
        if (fst == snd) {
            oldIndexOrInsertAndIndex(fst)
            return
        }

        val fstIndex = oldIndexOrInsertAndIndex(fst)
        val sndIndex = oldIndexOrInsertAndIndex(snd)

        if (fstIndex == sndIndex) return // both are already in the union
        else {
            // Element in union with index 'fstIndex' will now be in secondIndex. Other elements are not changed
            for (elem in elements) {
                if (elem.value == fstIndex) {
                    elements[elem.key] = sndIndex
                }
            }
        }
    }

    override fun connected(fst: T, snd: T): Boolean {
        // Assume same element in always connected to itself
        if (fst == snd) return true

        val fstIndex = oldIndexOrNull(fst)
        val sndIndex = oldIndexOrNull(snd)
        return fstIndex != null && sndIndex != null && fstIndex == sndIndex
    }

    private fun exist(elem: T) = elements.containsKey(elem)

    /** Get set index of element OR insert element in brand new union */
    private fun oldIndexOrInsertAndIndex(elem: T): Int {
        return if (exist(elem)) elements.getValue(elem)
        else {
            val curIndex = nextUnionIndex
            elements[elem] = curIndex
            nextUnionIndex++
            curIndex
        }
    }

    private fun oldIndexOrNull(elem: T): Int? = elements[elem]
}

//
//
//
//   Quick Union Union!
//
//

/** Union with fast 'union' operation O(1) and 'slow' 'connected' operation O(n) in worst case,
 * but can be optimized to O(log(n)) [QuickUnionUnionOptimized].
 * The idea is to add elements as children on union operation which will create long-long trees */
class QuickUnionUnion<T> : Union<T>() {

    /** Each element may or may not have a parent. If no parent available -> it's a root of tree */
    private val elementToParent: MutableMap<T, T?> = mutableMapOf()

    override fun union(fst: T, snd: T) {
        insertIfNotExist(fst)
        insertIfNotExist(snd)

        elementToParent[root(fst)] = root(snd)
    }

    override fun connected(fst: T, snd: T): Boolean = root(fst) == root(snd)


    // Can do like this but harder to read
    // private fun insertIfNotExist(elem: T) = elementToParent.computeIfAbsent(elem) { null }
    private fun insertIfNotExist(elem: T) {
        if (!elementToParent.containsKey(elem)) {
            elementToParent[elem] = null
        }
    }

    private fun root(elem: T): T {
        var prev = elem
        var current = elementToParent[prev]

        while (current != null) {
            prev = current
            current = elementToParent[prev]
        }

        return prev
    }

}


//
//
//   Quick Union Union Optimized!
//
//

/** Union with fast 'union' operation O(1) and 'slow' 'connected' operation O(log(n)).
 *
 * There are 2 optimizations:
 * 1. When joining 2 trees -> put smaller one to a root of a larger one (do not let large tree grow further)
 * 2. When finding a root of tree -> rebind all children closer to root */
class QuickUnionUnionOptimized<T> : Union<T>() {

    /** Each element may or may not have a parent. If no parent available -> it's a root of tree */
    private val elementToParent: MutableMap<T, T?> = mutableMapOf()

    override fun union(fst: T, snd: T) {
        insertIfNotExist(fst)
        insertIfNotExist(snd)

        // OPTIMIZATION 1 HERE!
        // Pick smaller of two trees when linking unions
        val rootFst = root(fst)
        val rootSnd = root(snd)
        if (rootFst.size < rootSnd.size) {
            elementToParent[rootFst.root] = rootSnd.root
        } else {
            elementToParent[rootSnd.root] = rootFst.root
        }
    }

    override fun connected(fst: T, snd: T): Boolean = root(fst).root == root(snd).root


    // Can do comment below but seems harder to read
    // private fun insertIfNotExist(elem: T) = elementToParent.computeIfAbsent(elem) { null }
    private fun insertIfNotExist(elem: T) {
        if (!elementToParent.containsKey(elem)) {
            elementToParent[elem] = null
        }
    }

    data class RootAndLength<T>(val root: T, val size: Int)
    private fun root(elem: T): RootAndLength<T> {

        var size = 0
        var prev = elem
        var current = elementToParent[prev]

        while (current != null) {
            val oldPrev = prev
            prev = current
            current = elementToParent[prev]
            size++

            // OPTIMIZATION 2 HERE!
            // Shrink tree on each iteration by rebinding the farthest element 1 step closer to root
            elementToParent[oldPrev] = prev
        }
        return RootAndLength(prev, size)
    }

}


//
//
// main() for testing
//
//


fun main() {

    // pick one of 3 implementations

//    val quickFindUnion = QuickFindUnion<Int>()
//    val quickFindUnion = QuickUnionUnion<Int>()
    val quickFindUnion = QuickUnionUnionOptimized<Int>()

    with(quickFindUnion) {
        union(1, 2)
        union(6, 5)
        union(2, 5)
        union(3, 7)

        this.printConnected(1, 2) // true
        this.printConnected(2, 5) // true
        this.printConnected(5, 6) // true
        this.printConnected(0, 5) // false
        this.printConnected(6, 2) // true
        this.printConnected(7, 3) // true
        this.printConnected(0, 0) // true
        this.printConnected(0, 4) // false
    }
}
