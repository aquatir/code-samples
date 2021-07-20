fun <T> swap(array: Array<T>, fstIndex: Int, sndIndex: Int) {
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

/** Assume everything before i is sorted.
 * Add another element to sorted part -> swap elements until current element is in it's correct place */
fun <T : Comparable<T>> insertionSort(array: Array<T>) {
    for (i in array.indices) {
        for (j in i downTo 1) {
            if (array[j] < array[j - 1]) {
                swap(array, j, j - 1)
            } else {
                break
            }
        }
    }
}

/** h-sort with sequence 3x+1 */
fun <T : Comparable<T>> shellSort(array: Array<T>) {
    var h = 1
    while (h < array.size / 3) {
        h = 3 * h + 1 // 1, 4, 13, 40...
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

    fun sort(array: Array<T>, auxArray: Array<T>, startIndex: Int, endIndex: Int) {

        if (endIndex <= startIndex) return
        val middle = startIndex + ((endIndex - startIndex) / 2)
        sort(array, auxArray, startIndex = 0, endIndex = middle)
        sort(array, auxArray, startIndex = middle + 1, endIndex = endIndex)

        merge(array, auxArray, leftStart = 0, leftEnd = middle, rightStart = middle + 1, rightEnd = endIndex)
    }

    val aux = array.copyOf()
    sort(array, aux, startIndex = 0, endIndex = array.lastIndex)
}

fun <T : Comparable<T>> bottomUpMergeSort(array: Array<T>) {
    val aux = array.copyOf()

    val n = array.size
    var step = 1
    while (step < n) { // this will iterate over arrays of 2, than 4, than 8, until the end

        var index = 0
        while (index < n - step) {
            merge(
                array,
                aux,
                leftStart = index,
                leftEnd = index + step - 1,
                rightStart = index + step,
                rightEnd = minOf(n - 1, index + step + step - 1)
            )
            index += step + step
        }
        step += step
    }

    /*
     * Implementation can be much shorter for language which support 3 part for-loops. It will be as simpel as
     * for (step = 1; step < n; step = step + step) {
     *     for (i = 0; i < n - step; i += step + step) {
     *         merge (array, aux, i, i + step - 1, i + step, minOf(n - 1, i + step + step - 1)
     *     }
     * }
     */
}

fun <T : Comparable<T>> merge(
    array: Array<T>,
    auxArray: Array<T>,
    leftStart: Int,
    leftEnd: Int,
    rightStart: Int,
    rightEnd: Int
) {
    for (i in leftStart..rightEnd) {
        auxArray[i] = array[i]
    }

    var left = leftStart
    var right = rightStart

    for (index in leftStart..rightEnd) {
        when {
            left > leftEnd -> {
                array[index] = auxArray[right]
                right++
            }
            right > rightEnd -> {
                array[index] = auxArray[left]
                left++
            }
            auxArray[left] <= auxArray[right] -> {
                array[index] = auxArray[left];
                left++
            }
            else -> {
                array[index] = auxArray[right];
                right++
            }
        }
    }
}


fun <T : Comparable<T>> quickSort(array: Array<T>) {

    fun partition(array: Array<T>, startLeft: Int, startRight: Int): Int {

        /*
        First element is used as partition element
        After outer while(true) loop completes, all element to the left of startLeft will be
        lower and all element to right of it will be higher.

        Here we start with startLeft and startRight + 1 because the first thing we do before comparing
        is ++left and --right
        */

        var left = startLeft
        var right = startRight + 1

        while (true) {

            while (array[++left] < array[startLeft]) {
                if (left == startRight) {
                    break
                }
            }

            while (array[startLeft] < array[--right]) {
                if (right == startLeft) {
                    break
                }
            }

            if (left >= right) break
            swap(array, left, right)
        }

        swap(array, startLeft, right)
        return right // right is now in place
    }

    fun sort(array: Array<T>, startLeft: Int, startRight: Int) {
        if (startRight <= startLeft) return

        val inCorrectPlaceIndex = partition(array, startLeft, startRight)

        sort(array, startLeft, inCorrectPlaceIndex - 1)
        sort(array, inCorrectPlaceIndex + 1, startRight)
    }

    array.shuffle()
    sort(array, 0, array.lastIndex)
}
