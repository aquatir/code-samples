package codesample.leetcode.easy;

import java.util.HashSet;

/**
 * 217. Contains Duplicate — https://leetcode.com/problems/contains-duplicate/description/
 */
public class _217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        var set = new HashSet<Integer>();
        for (var num: nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
