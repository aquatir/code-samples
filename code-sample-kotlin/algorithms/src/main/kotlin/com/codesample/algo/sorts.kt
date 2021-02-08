fun <T: Comparable<T>> swap(list: MutableList<T>, fstIndex: Int, sndIndex: Int) {
    val fstValue = list[fstIndex]
    list[fstIndex] = list[sndIndex]
    list[sndIndex] = fstValue
}

/** Find the smallest -> insert it to next position */
fun <T: Comparable<T>> selectionSort(list: List<T>): List<T> {
    val result = list.toMutableList()

    for (i in 0 until result.size) {
        var curMinIndex = i
        for (j in (i+1) until result.size) {
            if (result[j] < result[curMinIndex]) {
                curMinIndex = j
            }
        }
        swap(result, i, curMinIndex)
    }

    return result
}

fun <T: Comparable<T>> insertionSort(list: List<T>): List<T> {
    val result = list.toMutableList()
    TODO()
}

fun <T: Comparable<T>> shellSort(list: List<T>): List<T> {
    val result = list.toMutableList()
    TODO()
}

fun <T: Comparable<T>> mergeSort(list: List<T>): List<T> {
    val result = list.toMutableList()
    TODO()
}

fun <T: Comparable<T>> quickSort(list: List<T>): List<T> {
    val result = list.toMutableList()
    TODO()
}
