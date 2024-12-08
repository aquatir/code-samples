package codesample.leetcode.medium;

/**
 * 2090. K Radius Subarray Averages https://leetcode.com/problems/k-radius-subarray-averages/description/
 *
 */
public class _2090_KRadiusSubarrayAverages {
    public int[] getAverages(int[] nums, int k) {
        var n = nums.length;
        var leftSum = new long[n];

        leftSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftSum[i] = leftSum[i-1] + nums[i];
        }

        var answer = new int[n];

        // radius
        int divFactor = k*2 + 1;
        if (k == 0) {
            divFactor = 1;
        }

        if (divFactor > n) { // no wrap radius possible => early return
            for (int i = 0; i < n; i++) {
                answer[i] = -1;
            }
            return answer;
        }

        // before first radius
        for (int i = 0; i < k; i++) {
            answer[i] = -1;
        }

        // compute radius
        for (int i = k; i < n-k; i++) {
            // sum of elements between index i-k and i+k. We plus nums[i-k], because substract of leftSum[i-k] remove it
            var sum = (leftSum[i+k] - leftSum[i-k] + nums[i-k]) / divFactor;
            answer[i] = (int) sum;
        }

        // after last radius
        for (int i = n-k; i < n; i++) {
            answer[i] = -1;
        }

        return answer;
    }
}
