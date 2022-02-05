package codesample.other.educative._01_sliding_window;

/**
 * Given an array of positive numbers and a positive number ‘k,’ find the maximum sum of any contiguous subarray of size ‘k’.
 *
 * Input: [2, 1, 5, 1, 3, 2], k=3
 * Output: 9
 * Explanation: Subarray with maximum sum is [5, 1, 3].
 */
public class _02_MaxSumOfSubarrayOfK {

    int maxSumOfSubarrayOfK(int[] array, int k) {
        int windowSum = 0;
        int windowsStart = 0;
        int curMax = 0; // ??

        for (int windowsEnd = 0; windowsEnd < array.length; windowsEnd++) {
            windowSum += array[windowsEnd];

            if (windowsEnd > k - 1) {
                windowSum -= array[windowsStart];
                windowsStart++;
            }
            curMax = Math.max(curMax, windowSum);
        }

        return curMax;
    }

    public static void main(String[] args) {
        var t = new _02_MaxSumOfSubarrayOfK();
        System.out.println(t.maxSumOfSubarrayOfK(new int[]{2, 1, 5, 1, 3, 2}, 3)); // exp: 9: [5,1,3]
    }
}
