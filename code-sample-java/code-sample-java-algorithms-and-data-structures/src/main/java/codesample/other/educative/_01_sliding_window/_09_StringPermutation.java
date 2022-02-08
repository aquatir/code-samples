package codesample.other.educative._01_sliding_window;

import java.util.HashMap;
import java.util.Map;

public class _09_StringPermutation {
    public static boolean findPermutation(String str, String pattern) {
        // find the frequency in pattern
        // with each character, keep finding frequency in str.
        // When str grows to pattern.size -> check frequency maps.
        //   if equals -> return true
        //    else -> remove first char and continue.
        // return false at the very end.

        Map<Character, Integer> patternFreqMap = new HashMap<>();

        Map<Character, Integer> strFreqMap = new HashMap<>();
        char[] chars = str.toCharArray();
        int windowStart = 0;
        int patternLength = pattern.length();

        for (char c: pattern.toCharArray()) {
            patternFreqMap.put(c, patternFreqMap.getOrDefault(c, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < chars.length; windowEnd++) {
            char c = chars[windowEnd];
            strFreqMap.put(c, strFreqMap.getOrDefault(c, 0) + 1);

            // maybe shrink
            if (windowEnd - windowStart + 1 > patternLength) {
                char first = chars[windowStart];
                if (strFreqMap.get(first) == 1) {
                    strFreqMap.remove(first);
                } else {
                    strFreqMap.put(first, strFreqMap.get(first) - 1);
                }
                windowStart++;
            }

            // check if equal -> should work for two maps
            if (windowEnd - windowStart + 1 == patternLength) {
                if (strFreqMap.equals(patternFreqMap)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(_09_StringPermutation.findPermutation("oidbcaf", "abc"));            // exp true (bca ~ abc)
        System.out.println(_09_StringPermutation.findPermutation("odicf", "dc"));               // exp false
        System.out.println(_09_StringPermutation.findPermutation("bcdxabcdy", "bcdyabcdx"));    // exp true (they do match)
        System.out.println(_09_StringPermutation.findPermutation("aaacb", "abc"));              // exp true (acb ~ abc)
    }
}
