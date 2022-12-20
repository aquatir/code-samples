package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 412. Fizz Buzz — https://leetcode.com/problems/fizz-buzz/description/
 */
public class _412_FizzBuzz {
    class Solution {
        public List<String> fizzBuzz(int n) {
            List<String> result = new ArrayList<>(n);

            for (int i = 1; i <= n; i++) {
                boolean dividableBy3 = i % 3 == 0;
                boolean dividableBy5 = i % 5 == 0;

                String res = "";

                if (dividableBy3) {
                    res += "Fizz";
                }

                if (dividableBy5) {
                    res += "Buzz";
                }

                if (res.isEmpty()) {
                    res += String.valueOf(i);
                }

                result.add(res);
            }

            return result;
        }
    }
}
