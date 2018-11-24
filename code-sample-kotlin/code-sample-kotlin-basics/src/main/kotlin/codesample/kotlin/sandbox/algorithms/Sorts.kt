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

    fun merge (left: List<Int>, right: List<Int>): List<Int> {

        val mergedList = mutableListOf<Int>()
        var leftIter = 0
        var rightIter = 0

        val size = left.size

        while (leftIter < size && rightIter < size) {
            if (left[leftIter] <= right[rightIter]) {
                mergedList.add(left[leftIter])
                leftIter++
            } else {
                mergedList.add(right[rightIter])
                rightIter++
            }
        }


        if (leftIter == size)
            mergedList.addAll(right.subList(rightIter, size))
        else if (rightIter == size)
            mergedList.addAll(left.subList(leftIter, size))

        return mergedList
    }
}

fun main(args: Array<String>) {
    val sorts = Sorts()

    val left = listOf(3,5,9)
    val right = listOf(3,5,10)

    println(sorts.merge(left, right))

    var superList = listOf(9,8,7,6,5,4,3,2,1)
    println(sorts.recursiveMerge(superList))
}

