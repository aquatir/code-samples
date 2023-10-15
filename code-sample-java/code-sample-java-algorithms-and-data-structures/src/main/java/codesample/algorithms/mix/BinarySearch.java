package codesample.algorithms.mix;

import java.util.List;

public class BinarySearch {

    public static <T extends Comparable<T>> int search(List<T> sortedList, T value) {
        int left = 0;
        int right = sortedList.size() - 1;

        while (left <= right) {
            var mid = left + (right - left) / 2;
            var elementAtMid = sortedList.get(mid);
            if (elementAtMid.compareTo(value) == 0) {
                return mid;
            } else if (elementAtMid.compareTo(value) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
