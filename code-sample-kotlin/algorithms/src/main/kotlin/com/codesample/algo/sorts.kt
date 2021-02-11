fun <T : Comparable<T>> swap(array: Array<T>, fstIndex: Int, sndIndex: Int) {
    val fstValue = array[fstIndex]
    array[fstIndex] = array[sndIndex]
    array[sndIndex] = fstValue
}

/** Find the smallest -> insert it to next position */
fun <T : Comparable<T>> selectionSort(array: Array<T>) {

    for (i in array.indices) {
        var curMinIndex = i
        for (j in (i + 1) until array.size) {
            if (array[j] < array[curMinIndex]) {
                curMinIndex = j
            }
        }
        swap(array, i, curMinIndex)
    }
}

/** aka Bubble Sort. Assume everything before i is sorted.
 * Add another element to sorted part -> swap elements until current element is in it's correct place */
fun <T : Comparable<T>> insertionSort(array: Array<T>) {
    for (i in array.indices) {
        for (j in i downTo 1) {
            if (array[j] < array[j - 1]) {
                swap(array, j, j - 1)
            } else {
                continue
            }
        }
    }
}

/** h-sort with sequence 3x+1 */
fun <T : Comparable<T>> shellSort(array: Array<T>) {
    var h = 1
    while (h < array.size / 3) {
        h = 3*h + 1 // 1, 4, 13, 40...
    }

    while (h >= 1) {
        // h-sort array here with step == h
        for (i in h until array.size) {
            for (j in i downTo h step h) {
                if (array[j] < array[j - h]) {
                    swap(array, j, j - h)
                } else {
                    continue
                }
            }
        }

        h /= 3
    }
}

fun <T : Comparable<T>> mergeSort(array: Array<T>) {
    TODO()
}

fun <T : Comparable<T>> quickSort(array: Array<T>) {
    TODO()
}
