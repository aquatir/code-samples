package codesample.leetcode.easy;

import java.util.HashMap;

/**
 * 1426. Counting Elements — https://leetcode.com/problems/counting-elements/description/
 */
public class _1426_CountingElements {
    public int countElements(int[] arr) {
        // value => count
        var freq = new HashMap<Integer, Integer>();
        for (int num: arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        var answer = 0;
        for (var num: freq.keySet()) {
            var thisNum = freq.get(num);
            var nextNum = freq.getOrDefault(num + 1, 0);

            if (nextNum != 0) {
                answer += thisNum;
            }
        }

        return answer;
    }
}
