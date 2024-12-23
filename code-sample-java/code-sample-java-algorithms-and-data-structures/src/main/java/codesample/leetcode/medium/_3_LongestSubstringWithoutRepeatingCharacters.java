package codesample.leetcode.medium;

import java.util.HashMap;

/**
 * 3. Longest Substring Without Repeating Characters — https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class _3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        // sliding window. Keep non-repeating.
        // use freq map to keep track?

        var left = 0;
        var ans = 0;
        var freq = new HashMap<Character, Integer>();
        for (int right = 0; right < s.length(); right++) {

            // extend
            var ch = s.charAt(right);
            freq.put(ch, 1 + freq.getOrDefault(ch, 0));

            // fix by removing a duplicate of exactly the same character
            while (freq.get(ch) > 1) {
                var chLeft = s.charAt(left);
                freq.put(chLeft, freq.get(chLeft) - 1);
                left++;
            }

            // sum
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
