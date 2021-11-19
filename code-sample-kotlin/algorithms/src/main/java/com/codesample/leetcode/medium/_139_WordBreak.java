package com.codesample.leetcode.medium;

import java.util.List;

public class _139_WordBreak {
    static class Solution {

        // If there are multiple ways to segment a single word -> any greedy will work?
        // example: applePIEkek workd = applePI Ekek, apple, PIEk, lol -> no.
        // Matching first word will not work.

        // Does longest match always work?
        // example: applePIEkek workd = applePI Ekek, apple, PIEk, lol, applePIEke, m -> also no
        // Matching longers word will not work.
        public boolean wordBreak(String s, List<String> wordDict) {
            if (s.length() == 1) {
                return wordDict.contains(s);
            }
            return false; // TODO
        }
    }
}
