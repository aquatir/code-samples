package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 90. Subsets II — https://leetcode.com/problems/subsets-ii/
 * <p>
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class _90_SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // start with empty
        result.add(new ArrayList<>());
        Arrays.sort(nums);

        int endIndex = 0;
        for (int j = 0; j < nums.length; j++) {

            int startIndex = 0;
            if (j > 0 && nums[j - 1] == nums[j]) {
                startIndex = endIndex + 1;
            }
            endIndex = result.size() - 1;

            for (int i = startIndex; i <= endIndex; i++) {
                List<Integer> cur = new ArrayList<>(result.get(i));
                cur.add(nums[j]);
                result.add(cur);
            }
        }

        return result;
    }
}
