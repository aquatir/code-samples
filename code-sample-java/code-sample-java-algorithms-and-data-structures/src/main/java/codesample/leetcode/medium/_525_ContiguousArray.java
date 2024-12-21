package codesample.leetcode.medium;

import java.util.HashMap;

/**
 * 525. Contiguous Array — https://leetcode.com/problems/contiguous-array/description/?source=submission-noac
 */
public class _525_ContiguousArray {
    public int findMaxLength(int[] nums) {
        // can this be a sliding window?
        // what about prefix sum?

        if (nums.length < 2) {
            return 0;
        }

        var freq = new HashMap<Integer, Integer>();
        freq.put(0, -1);

        var ans = 0;
        var cur = 0;
        for (int i = 0; i < nums.length; i++) {
            // for each new number either increment or decrement cur
            var num = nums[i];
            if (num == 0) {
                cur -= 1;
            } else {
                cur += 1;
            }

            // if we have not seen a cur number before, put the position where we saw it FIRST in a hashmap
            // we put only the first position, because if we encounter the same number of 0s and 1s 2nd or 3rd time,
            // the first ever index is still going to result in the biggest subarray
            // if we have seen it before, it means that we have zig-zagged from lowest to highest point (e.g. from -2 to +2, back to -2 of count). The number of zeroes and ones will be equal between those 2 high points.

            // also note that we have put freq.put(0, -1) in the beginning
            // this is necessary to support a case where we go back to zero again after analyzing multiple elements.
            var seenBefore = freq.get(cur);
            if (seenBefore == null) {
                freq.put(cur, i);
            } else {
                ans = Math.max(ans, i - seenBefore);
            }
        }

        return ans;

    }
}
