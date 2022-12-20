package codesample.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. Ransom Note — https://leetcode.com/problems/ransom-note/description/
 */
public class _383_RansomNote {
    class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            Map<Character, Integer> freq = new HashMap<>();

            for (int i = 0; i < magazine.length(); i++) {
                char c = magazine.charAt(i);
                var curFreq = freq.getOrDefault(c, 0);
                freq.put(c, curFreq + 1);
            }

            for (int i = 0; i < ransomNote.length(); i++) {
                char c = ransomNote.charAt(i);
                Integer freqOfChar = freq.get(c);
                if (freqOfChar == null || freqOfChar == 0) {
                    return false;
                } else {
                    freq.put(c, freqOfChar - 1);
                }
            }

            return true;
        }
    }
}
