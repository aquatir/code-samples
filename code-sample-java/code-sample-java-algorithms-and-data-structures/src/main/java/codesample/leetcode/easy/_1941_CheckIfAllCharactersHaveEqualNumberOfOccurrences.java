package codesample.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class _1941_CheckIfAllCharactersHaveEqualNumberOfOccurrences {
    public boolean areOccurrencesEqual(String s) {
        // option 1: sort and iterate.

        // option 2: use frequency map (or array), check it at the end.
        Map<Character, Integer> freqMap = new HashMap<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        int rememberedAnswer = 0;
        for (char c : freqMap.keySet()) {
            int freq = freqMap.get(c);

            if (rememberedAnswer != 0) {
                if (freq != rememberedAnswer) {
                    return false;
                }
            } else {
                if (freq != 0) {
                    rememberedAnswer = freq;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        var s = new _1941_CheckIfAllCharactersHaveEqualNumberOfOccurrences();
        System.out.println(s.areOccurrencesEqual("abacbc"));    // exp: true
        System.out.println(s.areOccurrencesEqual("aaabb"));     // exp: false
        System.out.println(s.areOccurrencesEqual(""));          // exp: true
    }

}
