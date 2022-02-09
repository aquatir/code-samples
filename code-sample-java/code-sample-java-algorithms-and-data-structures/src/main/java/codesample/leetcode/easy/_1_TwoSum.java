package codesample.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class _1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // create a hashMap which would show the targets we already have
        // iterate over the array. If target - nums[i] is in the hashMap -> found a match
        // if not -> put it into array
        // if found nothing, return -1, -1

        Map<Integer, Integer> possibleTargets = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];

            if (possibleTargets.containsKey(target - value)) {
                return new int[]{possibleTargets.get(target - value), i};
            } else {
                possibleTargets.put(value, i);
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        var s = new _1_TwoSum();
        var res = s.twoSum(new int[]{3, 2, 4}, 6); // ex: [1,2]
        for (int r : res)
            System.out.print(r + ", ");

        System.out.println();
        res = s.twoSum(new int[]{3, 3}, 6); //ex: 0,1
        for (int r : res)
            System.out.print(r + ", ");
    }
}
