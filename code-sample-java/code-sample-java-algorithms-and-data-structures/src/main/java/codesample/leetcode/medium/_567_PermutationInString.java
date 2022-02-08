package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class _567_PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> patternFreqMap = new HashMap<>();
        for (char chr : s1.toCharArray()) {
            patternFreqMap.put(chr, patternFreqMap.getOrDefault(chr, 0) + 1);
        }

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < s2.length(); windowEnd++) {
            char charAtEnd = s2.charAt(windowEnd);
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

            if (windowEnd >= s1.length() - 1) { // shrink the window by one character
                char leftChar = s2.charAt(windowStart++);
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
        var s = new _567_PermutationInString();
        System.out.println(s.checkInclusion("ab", "eidbaooo")); // true
        System.out.println(s.checkInclusion("ab", "eidboaoo")); // false
    }
}
