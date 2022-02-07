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
        // TODO: Write your code here

        char[] chars = str.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        int curMaxLength = 0;
        int windowStart = 0;
        int curNumberOfChars = 0;

        for (int windowEnd = 0; windowEnd < chars.length; windowEnd++) {

            // add new char to map or increate current number of entries
            char c = chars[windowEnd];
            if (map.get(c) == null) {
                map.put(c, 1);
                curNumberOfChars++;
            } else {
                map.put(c, map.get(c) + 1);
            }

            // if move chars when should be — remove chars from begginning until it's k chars again
            while (curNumberOfChars > k && windowStart <= windowEnd) { // do we need this condition?
                char charAtStart = chars[windowStart];
                int numberOfEntries = map.get(charAtStart);
                if (numberOfEntries == 1) {
                    map.remove(charAtStart);
                    curNumberOfChars--;
                } else {
                    map.put(charAtStart, numberOfEntries - 1);
                }
                windowStart++;
            }

            // finally, calculate new max length;
            curMaxLength = Math.max(curMaxLength, windowEnd - windowStart + 1);
        }

        return curMaxLength;
    }

    public static void main(String[] args) {
        System.out.println(_04_LongestSubstringKDistinct.findLength("araaci", 2));      // exp: 4
        System.out.println(_04_LongestSubstringKDistinct.findLength("araaci", 1));      // exp: 2
        System.out.println(_04_LongestSubstringKDistinct.findLength("cbbebi", 3));      // exp: 5
        System.out.println(_04_LongestSubstringKDistinct.findLength("cbbebi", 10));     // exp: 6
    }
}
