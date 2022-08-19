package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. Summary Ranges — https://leetcode.com/problems/summary-ranges/
 * <p>
 * You are given a sorted unique integer array nums.
 * <p>
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 */
public class _228_SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        if (nums.length == 1) {
            result.add("" + nums[0]);
            return result;
        }

        int first = nums[0];
        int prev = first;

        for (int i = 1; i < nums.length; i++) {
            int second = nums[i];

            if (prev + 1 == second) {
                prev = second;
            } else {
                if (first == prev) {
                    result.add("" + first);
                } else {
                    result.add(first + "->" + prev);
                }

                first = second;
                prev = second;
            }
        }

        if (first == nums[nums.length - 1]) {
            result.add("" + first);
        } else {
            result.add(first + "->" + nums[nums.length - 1]);
        }

        return result;
    }
}
