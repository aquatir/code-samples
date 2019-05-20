package codesample.kotlin.sandbox.algorithms

class Sorts {

    fun recursiveMerge(list: List<Int>): List<Int> {
        if (list.size <= 1)
            return list

        val middle = list.size / 2
        val left = list.subList(0, middle)
        val right = list.subList(middle, list.size)

        return merge(recursiveMerge(left), recursiveMerge(right))
    }

    fun merge(left: List<Int>, right: List<Int>): List<Int> {

        val mergedList = mutableListOf<Int>()
        var leftIter = 0
        var rightIter = 0

        val leftSize = left.size
        val rightSize = right.size

        while (leftIter < leftSize && rightIter < rightSize) {
            if (left[leftIter] <= right[rightIter]) {
                mergedList.add(left[leftIter])
                leftIter++
            } else {
                mergedList.add(right[rightIter])
                rightIter++
            }
        }


        if (leftIter == leftSize)
            mergedList.addAll(right.subList(rightIter, rightSize))
        else if (rightIter == rightSize)
            mergedList.addAll(left.subList(leftIter, leftSize))

        return mergedList
    }
}

fun main(args: Array<String>) {
    val sorts = Sorts()

    val left = listOf(3, 5, 9)
    val right = listOf(3, 5, 10)

    println(sorts.merge(left, right))

    var superList = listOf(9, 8, 7, 6, 5, 4, 3, 2, 1)
    println(sorts.recursiveMerge(superList))
}

