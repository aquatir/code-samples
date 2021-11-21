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

        // Consider we know that a shorter string can be segmented.
        // Can it help us with segmenting the longer string?
        //
        // say that string up to k chars can always be segmented
        // then if (k-1) could be segmented:
        //  if there exist any substring from 0 to k: we can also segment full string
        //

        public boolean wordBreak(String s, List<String> wordDict) {
            if (s.length() == 1) {
                return wordDict.contains(s);
            }

            int n = s.length();
            boolean[] canSegmentUntil = new boolean[n + 1];

            canSegmentUntil[0] = true;

            for (int i = 1; i <= n; i++) { // this loop tells us if we can segment a string to point

                for (int j = 0; j < i; j++) { // this loop will find a segmented string if available
                    //

                    if (canSegmentUntil[j] && wordDict.contains(s.substring(j, i))) {
                        canSegmentUntil[i] = true;
                        break; // only need to find one possible substring
                    }
                }
            }

            return canSegmentUntil[n];
        }
    }
}
