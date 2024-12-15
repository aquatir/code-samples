package codesample.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class _1941_CheckIfAllCharactersHaveEqualNumberOfOccurrences {

    // use more memory for a concise solution: after getting frequencies, make sure the size of a set of different
    // frequencies is 1
    public boolean areOccurrencesEqualSimplestSlowerWithCollections(String s) {
        var freq = new HashMap<Character, Integer>();
        for (char ch: s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        var setOfFreq = new HashSet<Integer>(freq.values());
        return setOfFreq.size() == 1;
    }

    // count freq => take any frequency as "baseline", compare everything to it
    public boolean areOccurrencesEqualSimplest(String s) {
        var freq = new HashMap<Character, Integer>();
        for (char ch: s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        var targetFreq = freq.get(s.charAt(0));
        for (var entry: freq.entrySet()) {
            if (!entry.getValue().equals(targetFreq)) {
                return false;
            }
        }
        return true;
    }

    // count frequency, and also frequency of a first elements => compare all to first
    public boolean areOccurrencesEqualSimpler(String s) {

        // any char frequency will do, so we count first chars freqency
        var firstChar = s.charAt(0);
        var targetFreq = 0;

        var freq = new HashMap<Character, Integer>();
        for (char ch: s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            if (ch == firstChar) {
                targetFreq++;
            }
        }

        for (var value: freq.values()) {
            if (value != targetFreq) {
                return false;
            }
        }
        return true;
    }


    public boolean areOccurrencesEqual(String s) {
        // option 1: sort and iterate.
        // option 2: use frequency map (or array), check it at the end.
        Map<Character, Integer> freqMap = new HashMap<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // find first elements to compare everything too. Bad solution, look above for better ones
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
