package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 438. Find All Anagrams in a String — https://leetcode.com/problems/find-all-anagrams-in-a-string/
 *
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 */
public class _438_FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        // create a freq map of pattern
        // start freq map of string.
        // if frequency is the same for current part and pattern -> save index
        //
        // when the next element added to window will make is larger than it should be -> remove element
            // move the window
            // reduce freq on first char.
            // don't forget to add `null` instead of zero for map.

        Map<Character, Integer> patternMap = new HashMap<>();
        for (char c: p.toCharArray()) {
            patternMap.put(c, patternMap.getOrDefault(c, 0) + 1);
        }

        char[] chars = s.toCharArray();
        Map<Character, Integer> strMap = new HashMap<>();
        int windowStart = 0;
        int patternLength = p.length();
        List<Integer> indexes = new ArrayList<>();


        for (int windowEnd = 0; windowEnd < chars.length; windowEnd++) {
            char c = chars[windowEnd];

            // increate frequency
            strMap.put(c, strMap.getOrDefault(c, 0) + 1);

            // check existing match
            if (strMap.equals(patternMap)) {
                indexes.add(windowStart);
            }


            // if the next element will go over max -> delete first
            if (windowEnd >= patternLength - 1) {
                char atStart = chars[windowStart];
                strMap.put(atStart, strMap.get(atStart) - 1);
                if (strMap.get(atStart) == 0) {
                    strMap.remove(atStart);
                }
                windowStart++;
            }
        }

        return indexes;
    }


}
