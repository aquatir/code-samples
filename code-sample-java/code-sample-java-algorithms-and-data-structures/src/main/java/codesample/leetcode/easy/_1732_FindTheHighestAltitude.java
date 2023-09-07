package codesample.leetcode.easy;

/**
 * 1732. Find the Highest Altitude — https://leetcode.com/problems/find-the-highest-altitude/
 */
public class _1732_FindTheHighestAltitude {
    public int largestAltitude(int[] gain) {
        var max = 0;
        var cur = 0;

        for (var n : gain) {
            cur += n;
            max = Math.max(max, cur);
        }

        return max;
    }
}
