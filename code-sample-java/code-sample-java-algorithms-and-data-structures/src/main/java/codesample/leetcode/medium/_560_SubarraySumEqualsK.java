package codesample.leetcode.medium;

import java.util.HashMap;

/**
 * 560. Subarray Sum Equals K https://leetcode.com/problems/subarray-sum-equals-k/description/
 */
public class _560_SubarraySumEqualsK {

    /**
     * curr holds a left prefix sum
     * counts holds number of times a prefix sum was encountered
     * <br>
     * On each iteration i, we increase curr by nums[i], and then follow the logic:
     * <br>
     * We are searching for subarray [j, i] with a sum of k. We don't know where it starts, but with prefix sum we know
     * how to count its sum, it's prefixSum[i] - prefixSum[j-1]
     * <br>
     * If we were to draw it, we will also see this:
     * <br>
     * <pre>
     * [-----------cur-----------]
     * [0]--- [j-1] - [j] ---- [i] ---
     *                [-----k----]
     * </pre>
     *
     * so the sum of subarray between [0,j-1] is [cur - k]. There could be more than 1 way to get the [cur - k], so on each
     * iteration we also increase the count of "ways to get this subsume"
     */
    public int subarraySum(int[] nums, int k) {
        var curr = 0;

        // sum to number of times it was encountered
        var counts = new HashMap<Integer, Integer>();
        counts.put(0, 1);
        var answer = 0;

        for (int i = 0; i < nums.length; i++) {
            curr += nums[i];
            answer += counts.getOrDefault(curr - k, 0);
            counts.put(curr, counts.getOrDefault(curr, 0) + 1);
        }
        return answer;
    }
}
