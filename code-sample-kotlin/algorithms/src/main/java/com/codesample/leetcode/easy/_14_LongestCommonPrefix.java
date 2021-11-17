package com.codesample.leetcode.easy;

/**
 * 14. Longest Common Prefix — https://leetcode.com/problems/longest-common-prefix/
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 */
public class _14_LongestCommonPrefix {
    static class Solution {

        public String longestCommonPrefix(String[] strs) {
            // find min str -> only compare by min => 2 passes
            // remember index and result of current and next iterations
            // => when encountering NON-matching -> keep going full n to see if some can not complete.

            String minString = strs[0];
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() < minString.length()) {
                    minString = strs[i];
                }
            }

            for (int i = 0; i < minString.length(); i++) {
                char c = minString.charAt(i);

                for (int j = 0; j < strs.length; j++) {
                    if (strs[j].charAt(i) != c) {
                        return minString.substring(0, i);
                    }
                }
            }

            return minString;
        }
    }
}
