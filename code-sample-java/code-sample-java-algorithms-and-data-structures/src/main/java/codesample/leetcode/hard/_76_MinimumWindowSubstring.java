package codesample.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring — https://leetcode.com/problems/minimum-window-substring/
 *
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every
 * character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * A substring is a contiguous sequence of characters within the string.
 */
public class _76_MinimumWindowSubstring {
    public String minWindow(String s, String t) {


        int windowStart = 0;
        int matched = 0;
        int curMatchLength = s.length() + 1;
        int subStrStart = 0;

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char c = s.charAt(windowEnd);

            // reduce freq of character
            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) - 1);

                if (freqMap.get(c) >= 0) {
                    matched++;
                }
            }

            // shrink the window if we can, finish as soon as we remove a matched character
            while (matched == t.length()) {
                if (curMatchLength > windowEnd - windowStart + 1) {
                    curMatchLength = windowEnd - windowStart + 1;
                    subStrStart = windowStart;
                }

                char charAtStart = s.charAt(windowStart++);
                if (freqMap.containsKey(charAtStart)) {
                    if (freqMap.get(charAtStart) == 0) {
                        matched--;
                    }
                    freqMap.put(charAtStart, freqMap.get(charAtStart) + 1);
                }
                
            }
        }
        return curMatchLength > s.length() ? "" : s.substring(subStrStart, subStrStart + curMatchLength);
    }
}
