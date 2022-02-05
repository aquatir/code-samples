package codesample.other.educative._01_sliding_window;

/**
 * Given an array of positive numbers and a positive number ‘S,’ find the length of the smallest contiguous subarray
 * whose sum is greater than or equal to ‘S’. Return 0 if no such subarray exists.
 * <p>
 * Example 1:
 * Input: [2, 1, 5, 2, 3, 2], S=7
 * Output: 2
 * Explanation: The smallest subarray with a sum greater than or equal to '7' is [5, 2].
 * <p>
 * Examples 2:
 * Input: [2, 1, 5, 2, 8], S=7
 * Output: 1
 * Explanation: The smallest subarray with a sum greater than or equal to '7' is [8].
 */
class _03_MinSizeSubArraySum {
    public static int findMinSubArray(int target, int[] nums) {
        int n = nums.length;
        int smallestWindowSoFar = n;
        boolean hit = false;

        int windowStart = 0;
        int currentWindowsSum = 0;

        for (int windowEnd = 0; windowEnd < n; windowEnd++) {

            currentWindowsSum += nums[windowEnd];

            if (currentWindowsSum >= target) {
                while (currentWindowsSum - nums[windowStart] >= target) {
                    currentWindowsSum -= nums[windowStart];
                    windowStart++;
                }

                smallestWindowSoFar = Math.min(smallestWindowSoFar, windowEnd - windowStart + 1);
                hit = true;
            }
        }

        return hit ? smallestWindowSoFar : 0;
    }

    public static void main(String[] args) {
        System.out.println(_03_MinSizeSubArraySum.findMinSubArray(7, new int[]{2, 3, 1, 2, 4, 3})); // exp 2
    }
}


