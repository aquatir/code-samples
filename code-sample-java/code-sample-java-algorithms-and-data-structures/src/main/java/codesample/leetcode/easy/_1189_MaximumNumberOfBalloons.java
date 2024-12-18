package codesample.leetcode.easy;

import java.util.HashMap;

/**
 * 1189. Maximum Number of Balloons — https://leetcode.com/problems/maximum-number-of-balloons/description/
 */
public class _1189_MaximumNumberOfBalloons {

    public int maxNumberOfBalloons(String text) {
        var freq = new HashMap<Character, Integer>();
        for (var ch: text.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        // b = 1
        // a = 1
        // l == 2
        // o == 2
        // n = 1

        var balloons = 0;
        while (true) {
            var b = freq.getOrDefault('b', 0);
            var a = freq.getOrDefault('a', 0);
            var l = freq.getOrDefault('l', 0);
            var o = freq.getOrDefault('o', 0);
            var n = freq.getOrDefault('n', 0);
            if (b >= 1 && a >= 1 && l >= 2 && o >= 2 && n >= 1) {
                balloons++;
                freq.put('b', b - 1);
                freq.put('a', a - 1);
                freq.put('l', l - 2);
                freq.put('o', o - 2);
                freq.put('n', n - 1);
            } else {
                break;
            }
        }

        return balloons;
    }
}
