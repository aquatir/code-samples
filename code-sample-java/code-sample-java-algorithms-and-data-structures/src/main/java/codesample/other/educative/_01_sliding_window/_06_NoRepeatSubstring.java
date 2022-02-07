package codesample.other.educative._01_sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring, which has all distinct characters.
 *
 * Example 1
 * Input: String="aabccbb"
 * Output: 3
 * Explanation: The longest substring with distinct characters is "abc".
 *
 * Example 2
 * Input: String="abbbb"
 * Output: 2
 * Explanation: The longest substring with distinct characters is "ab".
 *
 * Example 3
 * Input: String="abccde"
 * Output: 3
 * Explanation: Longest substrings with distinct characters are "abc" & "cde".
 */
public class _06_NoRepeatSubstring {
    public static int findLength(String str) {

        // get a character
        // if it's a new character -> increase current max sequence.
        // if it's not -> limit the size of sequence to the next after previous char met.
        //    for this, we can map char -> int where int is the index.
        //    when we get matching character (an entry exist in index)
        //      we checkout the next character after this one: this is a possible new unique string.
        //

        // abcabcd -> a: start with b at position 1. that is get a + 1.
        // abccab  -> c: start with

        char[] chars = str.toCharArray();
        Map<Character, Integer> index = new HashMap<>();
        int curMaxUniqueLength = 0;
        int curStartIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (index.containsKey(ch)) {
                int otherCharIndex = index.get(ch);

                // The previous char is the same as current
                if (i - otherCharIndex == 1) {
                    curStartIndex = i;
                } else {
                    curStartIndex = otherCharIndex + 1;
                }
            }
            index.put(ch, i);
            curMaxUniqueLength = Math.max(curMaxUniqueLength, i - curStartIndex + 1);
        }

        return curMaxUniqueLength;
    }

    public static void main(String[] args) {
        System.out.println(_06_NoRepeatSubstring.findLength("aabccbb")); // exp 3
        System.out.println(_06_NoRepeatSubstring.findLength("abbbb"));   // exp 2
        System.out.println(_06_NoRepeatSubstring.findLength("abccde")); // exp 3
    }
}
