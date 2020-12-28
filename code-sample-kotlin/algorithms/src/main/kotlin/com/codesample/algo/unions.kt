package com.codesample.algo


abstract class Union<T : Comparable<T>>() {
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

/** Union with fast 'connected' operation o(1) and slow 'union' o(n)
 *
 * The entries are connected if and only if they have the same index.
 * Implementation is NOT thread-safe!*/
class QuickFindUnion<T : Comparable<T>>() : Union<T>() {

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


fun main() {
    val quickFindUnion = QuickFindUnion<Int>()

    with(quickFindUnion) {
        union(1, 2)
        union(2, 5)
        union(6, 5)
        union(3, 7)

        this.printConnected(1, 2) // true
        this.printConnected(0, 5) // false
        this.printConnected(6, 2) // true
        this.printConnected(7, 3) // true
        this.printConnected(0, 0) // true
        this.printConnected(0, 4) // false
    }

}


//
//
//
//   Quick Union Union!
//
//

/** Union with fast 'union' operation */
class QuickUnionUnion<T : Comparable<T>>() : Union<T>() {
    override fun union(fst: T, snd: T) {
        TODO("Not yet implemented")
    }

    override fun connected(fst: T, snd: T): Boolean {
        TODO("Not yet implemented")
    }
}
