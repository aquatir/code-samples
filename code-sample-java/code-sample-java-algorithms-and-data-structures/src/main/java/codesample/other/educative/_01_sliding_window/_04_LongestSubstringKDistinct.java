package codesample.other.educative._01_sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 * <p>
 * Example 1:
 * Input: String="araaci", K=2
 * Output: 4
 * Explanation: The longest substring with no more than '2' distinct characters is "araa".
 * <p>
 * Example 2:
 * Input: String="araaci", K=1
 * Output: 2
 * Explanation: The longest substring with no more than '1' distinct characters is "aa".
 * <p>
 * Example 3:
 * Input: String="cbbebi", K=3
 * Output: 5
 * Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".
 * <p>
 * Example 4:
 * Input: String="cbbebi", K=10
 * Output: 6
 * Explanation: The longest substring with no more than '10' distinct characters is "cbbebi".
 */
class _04_LongestSubstringKDistinct {
    public static int findLength(String str, int k) {


        // input: string -> char[] would be easier
        // k - number of letter we could replace.

        // k == 26 -> whose sting
        // k == 0 -> just longest substring of uniques
        // if (str is null or has 1 char -> return 0 or 1)

        int windowStart = 0;
        int charMaxRepeats = 0;
        int curMax = 0;
        char[] chars = str.toCharArray();
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();

        for (int windowEnd = 0; windowEnd < chars.length; windowEnd++) {
            char charAtEnd = chars[windowEnd];
            freqMap.put(charAtEnd, freqMap.getOrDefault(charAtEnd, 0) + 1);
            charMaxRepeats = Math.max(charMaxRepeats, freqMap.get(charAtEnd));

            // possible shrink window
            if (windowEnd - windowStart + 1 - charMaxRepeats > k) {
                char charAtStart = chars[windowStart];
                freqMap.put(charAtStart, freqMap.get(charAtStart) - 1);
                windowStart++;
            }
            // calculate max
            curMax = Math.max(curMax, windowEnd - windowStart + 1);
        }

        return curMax;
    }

    public static void main(String[] args) {
        System.out.println(_04_LongestSubstringKDistinct.findLength("araaci", 2));      // exp: 4
        System.out.println(_04_LongestSubstringKDistinct.findLength("araaci", 1));      // exp: 2
        System.out.println(_04_LongestSubstringKDistinct.findLength("cbbebi", 3));      // exp: 5
        System.out.println(_04_LongestSubstringKDistinct.findLength("cbbebi", 10));     // exp: 6
    }
}
