package codesample.other.crackingthecodinginterview.chapter1;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

// Given 2 string write a method to decide if one is a permutation of another
public class _0012_IsPermutation {
    public boolean isPermutationBruteForce(String one, String two) {
        // 1. find the shortest of 2 strings

        // BRUTE FORCE: sort whole short string => take a length of short string window in long string => compare
        // optimization: count frequency in a short string. Count frequency in a substring of long string by moving a sliding window
        // when frequency maps are same => true, else false

        String shortStr;
        String longStr;
        if (one.length() > two.length()) {
            longStr = one;
            shortStr = two;
        } else {
            longStr = two;
            shortStr = one;
        }

        // brute force
        var shortCharArray = shortStr.toCharArray();
        Arrays.sort(shortCharArray);

        var start = 0;
        var end = shortCharArray.length; // must not be included

        while (end <= longStr.length()) {
            var longSubString = longStr.substring(start, end);
            var longSubStringCharArray = longSubString.toCharArray();
            Arrays.sort(longSubStringCharArray);

            if (Arrays.equals(shortCharArray, longSubStringCharArray)) {
                return true;
            }
            start++;
            end++;
        }
        return false;
    }

    public boolean isPermutatioFrequencyMap(String one, String two) {
        // 1. find the shortest of 2 strings

        // optimization: count frequency in a short string. Count frequency in a substring of long string by moving a sliding window
        // when frequency maps are same => true, else false

        String shortStr;
        String longStr;
        if (one.length() > two.length()) {
            longStr = one;
            shortStr = two;
        } else {
            longStr = two;
            shortStr = one;
        }

        Map<Character, Integer> shortFrequency = countStringFrequency(shortStr);

        var longStrCharArray = longStr.toCharArray();
        var curFreqMap = new HashMap<Character, Integer>();
        var start = 0;
        var end = shortStr.length() - 1; // inclusive

        for (int i = start; i <= end; i++) {
            curFreqMap.put(longStrCharArray[i], curFreqMap.getOrDefault(longStrCharArray[i], 0) + 1);
        }

        while (end < longStr.length()) {
            if (shortFrequency.equals(curFreqMap)) {
                return true;
            } else {
                // need to move start, end and change curFreqMap
                var charAtStart = longStrCharArray[start];
                var firstCharFreq = curFreqMap.get(charAtStart);
                if (firstCharFreq == 1) {
                    curFreqMap.remove(charAtStart); // remove the mapping if it was the last character
                } else {
                    curFreqMap.put(charAtStart, firstCharFreq - 1); // or else reduce freq by 1
                }
                start++;
                end++;
                if (end < longStr.length()) {
                    curFreqMap.put(longStrCharArray[end], curFreqMap.getOrDefault(longStrCharArray[end], 0) + 1);
                }
            }
        }
        return false;
    }


    private Map<Character, Integer> countStringFrequency(String shortStr) {
        var map = new HashMap<Character, Integer>();
        for (var ch: shortStr.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        var s= new _0012_IsPermutation();
//        BiFunction<String, String, Boolean> func = s::isPermutationBruteForce;
        BiFunction<String, String, Boolean> func = s::isPermutatioFrequencyMap;

        System.out.println(func.apply("aaaa", "bbaa"));                     // false
        System.out.println(func.apply("abcd", "abcd"));                     // true
        System.out.println(func.apply("abcd", "dcba"));                     // true
        System.out.println(func.apply("iknjkkahdbacjbscd", "dcba"));        // true
        System.out.println(func.apply("iknjkkahdbacjbscdbdca", "dcba"));    // true
        System.out.println(func.apply("zdcblabcidca", "dcba"));             // false
    }
}
