package codesample.leetcode.medium;

/**
 * 852. Peak Index in a Mountain Array — https://leetcode.com/problems/peak-index-in-a-mountain-array/
 * <p>
 * An array arr a mountain if the following properties hold:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array arr, return the index i such that arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 * <p>
 * You must solve it in O(log(arr.length)) time complexity.
 */
public class _852_PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // either left is less, right is more (still climbing, move left)
            // or left is more, right is less (overclimbed, move right)
            // or left is less, right is less (exact)

            if (mid != 0 && mid != arr.length - 1 && arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else if (mid != 0 && mid != arr.length - 1 && arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1]) {
                right = mid - 1;
            } else {
                return mid == 0 ? 1 : mid;
            }
        }

        return -1;
    }
}
