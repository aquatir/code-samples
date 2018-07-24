package codesample.algorithms;

/**
 *
 * @author Pavel Bukhmatov (buhmatov@gmail.com; github.com/aquatir)
 */
public class Sorts {
    
    /** Sort array using selection sort
     * 
     * @param array input array.
     * @param left left index of array. Included in sort
     * @param right right index of array. NOT INCLUDED in sort
     */
    public static void selectionSort(Comparable[] array, int left, int right) {
        for (int i = left; i < right; ++i) {
            int minIndex = i;
            int j;
            for (j = i; j < right; ++j) {
                if (less( array[j],array[minIndex]) )
                    minIndex = j;
            }
            swap (array, minIndex, i);
        }
    }

    /** Sort array using insertion sort (aka bubble sort)
     *
     * @param array input array.
     * @param left left index of array. Included in sort
     * @param right right index of array. NOT INCLUDED in sort 
     */
    public static void insertionSort(Comparable[] array, int left, int right) {
        for (int i = left; i < right; ++i) {
            for (int j = left + 1; j < right - i; ++j) {
                if (less( array[j], array[j-1] ) )
                    swap(array, j - 1 , j);
            }
        }
    }

    /** Sort array using shell sort based on 3x+1 sequence
     *
     * @param array input array.
     * @param left left index of array. Included in sort
     * @param right right index of array. NOT INCLUDED in sort
     */
    public static void shellSort(Comparable[] array, int left, int right) {
        int N = right - left;

        /* generate maximum h value */
        int h = 1;
        while (h < N/3)
            h = h * 3 + 1;

        /* start sorting */
        while (h >= 1) {
            for (int i = h; i < N; i += h) {
                for (int j = i; j >= h && less(array[j], array[j-h]); j -= h)
                    swap(array, j, j-h);
            }
            h = h/3;
        }
    }

    static void mergeSort (Comparable[] array, int left, int right) {
        
    }
    
    
    static void quickSort (Comparable[] array, int left, int right) {
        
    }

    
    private static void swap(Comparable[] array, int i, int j) {
        Comparable tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    private static boolean less (Comparable a, Comparable b)
    {
        if (a.compareTo(b) < 0)
            return true;
        else
            return false;
    }
}
