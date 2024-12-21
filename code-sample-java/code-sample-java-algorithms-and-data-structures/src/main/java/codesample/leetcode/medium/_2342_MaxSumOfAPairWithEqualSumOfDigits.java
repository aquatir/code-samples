package codesample.leetcode.medium;

import java.util.HashMap;

/**
 * 2342. Max Sum of a Pair With Equal Sum of Digits — https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/description/
 */
public class _2342_MaxSumOfAPairWithEqualSumOfDigits {
    public int maximumSum(int[] nums) {
        // store a map of sum-of-digits to the biggest number seen so far
        // when encountering the second time, recalculate max, and maybe update the map
        var max = -1;

        // sum to max number
        var map = new HashMap<Integer, Integer>();
        for (var num: nums) {
            var sumOfDigits = sumOfDigits(num);

            if (map.containsKey(sumOfDigits)) {
                var previous = map.get(sumOfDigits);
                max = Math.max(max, previous + num);

                // only update map, if current number is bigger, else previous is better to pick
                if (num > previous) {
                    map.put(sumOfDigits, num);
                }
            } else {
                map.put(sumOfDigits, num);
            }
        }

        return max;
    }

    private int sumOfDigits(int num) {
        var sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
