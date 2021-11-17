package com.codesample.leetcode.easy;

import java.util.HashMap;

/**
 * 13. Roman to Integer — https://leetcode.com/problems/roman-to-integer/
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is
 * simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
 * Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same
 * principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 */
public class RomanToInteger_13 {
    static class Solution {
        public int romanToInt(String s) {
            HashMap<String, Integer> romanToNumbers = new HashMap<>();

            romanToNumbers.put("I", 1);
            romanToNumbers.put("V", 5);
            romanToNumbers.put("X", 10);
            romanToNumbers.put("L", 50);
            romanToNumbers.put("C", 100);
            romanToNumbers.put("D", 500);
            romanToNumbers.put("M", 1000);

            if (s.length() == 1) {
                return romanToNumbers.get(s);
            }

            int cur = romanToNumbers.get(s.substring(0, 1));
            int prev = 0;
            int total = cur;

            for (int i = 1; i < s.length(); i++) {
                prev = cur;
                cur = romanToNumbers.get(s.substring(i, i+1));

                if (cur > prev) {
                    // because we have added in once on previous step now we substract it twice.
                    total -= 2* prev;
                }

                total += cur;
            }

            return total;

        }
    }
}
