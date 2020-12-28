package com.codesample.algo


abstract class Union<T : Comparable<T>>() {
    abstract fun union(fst: T, snd: T)
    abstract fun connected(fst: T, snd: T): Boolean
}

fun Union<Int>.printConnected(fst: Int, snd: Int) {
    if (this.connected(fst, snd))
        println("'$fst' and '$snd' are connected") else
        println("'$fst' and '$snd' are NOT connected")
}

/** Union with fast 'find' operation n(1)
 *
 * The entries are connected if and only if they have the same index.
 * Implementation is NOT thread-safe!*/
class QuickFindUnion<T : Comparable<T>>() : Union<T>() {

    /** Map element to numbered 'union' */
    private var nextUnionIndex = 0
    private val elements: MutableMap<T, Int> = mutableMapOf()

    override fun union(fst: T, snd: T) {
        // Do not try inserting same element again
        if (fst == snd) return

        val fstIndex = oldIndexOrInsertAndIndex(fst)
        val sndIndex = oldIndexOrInsertAndIndex(snd)

        if (fstIndex == sndIndex) return
        else {
            // Element in union with index 'fstIndex' will now be in secondIndex. Other elements continue to have their own index
            for (elem in elements) {
                if (elem.value == fstIndex) {
                    elements[elem.key] = sndIndex
                }
            }
        }
    }

    override fun connected(fst: T, snd: T): Boolean {
        // Same element in always connected to itself
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

    private fun oldIndexOrNull(elem: T): Int? {
        return if (exist(elem)) elements.getValue(elem)
        else null
    }
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
