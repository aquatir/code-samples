package codesample.other.educative._01_sliding_window;

import java.util.HashMap;
import java.util.Map;

public class _09_StringPermutationOptimization {
    public static boolean findPermutation(String str, String pattern) {
        // find the frequency in pattern
        // with each character, keep finding frequency in str.
        // When str grows to pattern.size -> check frequency maps.
        //   if equals -> return true
        //    else -> remove first char and continue.
        // return false at the very end.

        // OPTIMIZATION
        // We make a check on whole two maps which repeat a lot of elements checks
        // A better way would be to just check is the following:
        // Have a frequency map for pattern
        // on each element, reduce the frequency of said element.
        // when the frequency hit zero -> we got a match over a specific character: increase some variable by 1;
        // when shrinking the windows -> reduce the number of matches while also increasing the frequency of
        // non-matched character

        int windowStart = 0, matched = 0;
        Map<Character, Integer> patternFreqMap = new HashMap<>();
        for (char chr : pattern.toCharArray()) {
            patternFreqMap.put(chr, patternFreqMap.getOrDefault(chr, 0) + 1);
        }

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char charAtEnd = str.charAt(windowEnd);
            if (patternFreqMap.containsKey(charAtEnd)) {
                // decrement the frequency of the matched character
                patternFreqMap.put(charAtEnd, patternFreqMap.get(charAtEnd) - 1);

                // character is completely matched
                if (patternFreqMap.get(charAtEnd) == 0) {
                    matched++;
                }
            }

            if (matched == patternFreqMap.size()) {
                return true;
            }

            if (windowEnd >= pattern.length() - 1) { // shrink the window by one character
                char leftChar = str.charAt(windowStart++);
                if (patternFreqMap.containsKey(leftChar)) {

                    // before putting the character back, decrement the matched count
                    if (patternFreqMap.get(leftChar) == 0) {
                        matched--;
                    }

                    // put the character back for matching
                    patternFreqMap.put(leftChar, patternFreqMap.get(leftChar) + 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(_09_StringPermutationOptimization.findPermutation("oidbcaf", "abc"));            // exp true (bca ~ abc)
        System.out.println(_09_StringPermutationOptimization.findPermutation("odicf", "dc"));               // exp false
        System.out.println(_09_StringPermutationOptimization.findPermutation("bcdxabcdy", "bcdyabcdx"));    // exp true (they do match)
        System.out.println(_09_StringPermutationOptimization.findPermutation("aaacb", "abc"));              // exp true (acb ~ abc)
    }
}
