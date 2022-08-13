package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets — https://leetcode.com/problems/subsets/
 * <p>
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class _78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<Integer>());
        for (int num : nums) {

            int subsetsSize = subsets.size();
            for (int i = 0; i < subsetsSize; i++) {
                var existingSubset = subsets.get(i);
                List<Integer> newSubSet = new ArrayList<>(existingSubset);
                newSubSet.add(num);
                subsets.add(newSubSet);
            }
        }
        return subsets;
    }
}
