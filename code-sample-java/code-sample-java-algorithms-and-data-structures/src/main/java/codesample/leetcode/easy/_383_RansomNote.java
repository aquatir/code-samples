package codesample.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 383. Ransom Note — https://leetcode.com/problems/ransom-note/description/
 */
public class _383_RansomNote {
    class Solution {

        // faster coding solution
        public boolean canConstruct(String ransomNote, String magazine) {
            var freq = new HashMap<Character, Integer>();
            for (var c: magazine.toCharArray()) {
                freq.put(c, 1 + freq.getOrDefault(c, 0));
            }

            for (var c: ransomNote.toCharArray()) {
                if (freq.getOrDefault(c, 0) == 0) {
                    return false;
                } else {
                    freq.put(c, freq.get(c) - 1);
                }
            }

            return true;
        }


        // old solution
        public boolean canConstructTwoFrequencies(String ransomNote, String magazine) {
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
