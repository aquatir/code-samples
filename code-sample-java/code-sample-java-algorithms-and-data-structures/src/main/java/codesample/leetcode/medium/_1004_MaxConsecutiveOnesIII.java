package codesample.leetcode.medium;

/**
 * 1004. Max Consecutive Ones III — https://leetcode.com/problems/max-consecutive-ones-iii/
 * <p>
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip
 * at most k 0's.
 */
public class _1004_MaxConsecutiveOnesIII {

    // Approach 1: sliding window but slightly magical "shrink" function
    // approach 2: below: a much clearer shrink function
    public int longestOnes(int[] nums, int k) {
        int windowStart = 0;
        int max = 0;
        int maxOnesCount = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            if (nums[windowEnd] == 1) {
                maxOnesCount++;
            }

            // maybe shrink
            if (windowEnd - windowStart + 1 - maxOnesCount > k) {
                if (nums[windowStart] == 1) {
                    maxOnesCount--;
                }
                windowStart++;
            }

            // max
            max = Math.max(max, windowEnd - windowStart + 1);

        }

        return max;
    }

    public int longestOnesSimpler(int[] nums, int k) {
        // flip 0 to 1 to create a larger consecutive array.
        // if we have a sliding window and there are [k] zeroes somewhere inside this window
        // we can count them all as consecutive.

        // how can we move such window forward?
        //  assume we have left, right borders and between [left, right] there are k zeroes

        // we try to add another number on right
        // if it's one => extend window
        // if it's a zero =>
        //  remove last known zero
        //  reduce the length after removing this zero

        int left = 0;
        int canHaveOnes = k;
        int max = 0;

        for (int right = 0; right < nums.length; right++) {
            int newNum = nums[right];

            // if added a 1 => simply increment right and continue
            // else, need to see how to move the window properly
            if (newNum == 0) {
                // can add 0 to the range => simply do it
                if (canHaveOnes > 0) {
                    canHaveOnes--;
                } else {
                    // when can't add an extra zero => have to move left until first non-zero is encountered, increment possible number of zeroes by 1
                    // we don't care about canHaveOnes var anymore, because we have just added a zero, so it's + and instantly - one zero
                    // hence this branch is always executed
                    while (left <= right && nums[left] != 0) {
                        left++;
                    }
                    // nums[left] == 0 now. This is the 0 we want to skip, so skip it by moving left
                    left++;
                }
            }
            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    public int longestOnesEvenSimpler(int[] nums, int k) {
        var maxLength = 0;
        var zeroesRemaining = k;
        var left = 0;

        for (int right = 0; right < nums.length; right++) {
            // add to window
            if (nums[right] == 0) {
                zeroesRemaining--;
            }

            // make window valid
            while (zeroesRemaining < 0) {
                if (nums[left] == 0) {
                    zeroesRemaining++;
                }
                left++;
            }
            // calculate
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        var s = new _1004_MaxConsecutiveOnesIII();
        System.out.println(s.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2)); // exp: 6
        //System.out.println(s.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3)); // exp: 10
    }
}
