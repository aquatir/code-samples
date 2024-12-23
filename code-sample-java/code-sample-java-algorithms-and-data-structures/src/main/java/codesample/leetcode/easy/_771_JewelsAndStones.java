package codesample.leetcode.easy;

import java.util.HashSet;

/**
 * 771. Jewels and Stones — https://leetcode.com/problems/jewels-and-stones/description/
 */
public class _771_JewelsAndStones {
    public int numJewelsInStones(String jewels, String stones) {
        var jewelsSet = new HashSet<Character>();
        for (var c: jewels.toCharArray()) {
            jewelsSet.add(c);
        }

        int count = 0;
        for (var c: stones.toCharArray()) {
            count += jewelsSet.contains(c) ? 1: 0;
        }
        return count;
    }
}
