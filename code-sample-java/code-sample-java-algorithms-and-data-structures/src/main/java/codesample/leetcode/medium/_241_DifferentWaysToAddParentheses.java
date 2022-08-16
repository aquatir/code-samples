package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. Different Ways to Add Parentheses — https://leetcode.com/problems/different-ways-to-add-parentheses/
 * <p>
 * Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.
 * <p>
 * The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.
 */
public class _241_DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        // 2-1-1
        // 2-1 either compute to 1 or don't 2-1
        // 1-1 and 2-(1-1).
        // First: compute: 0,
        // second: 0

        // 2*3-4*5 => [6-4*5] or 2*(3-4*5)
        // 2*5 or 6-(4*5) or 2*(-1*5) or 2*(3-(4*5))

        List<Integer> result = new ArrayList<>();

        // one big number
        if (!expression.contains("+") && !expression.contains("-") && !expression.contains("*")) {
            result.add(Integer.parseInt(expression));
            return result;
        }

        // all other cases
        for (int i = 0; i < expression.length(); i++) {
            char chr = expression.charAt(i);
            if (!Character.isDigit(chr)) {

                List<Integer> leftParts = diffWaysToCompute(expression.substring(0, i));
                List<Integer> rightParts = diffWaysToCompute(expression.substring(i + 1));

                for (Integer leftPart : leftParts) {
                    for (Integer rightPart : rightParts) {
                        if (chr == '+') {
                            result.add(leftPart + rightPart);
                        }
                        if (chr == '-') {
                            result.add(leftPart - rightPart);
                        }
                        if (chr == '*') {
                            result.add(leftPart * rightPart);
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        var s = new _241_DifferentWaysToAddParentheses();
        System.out.println(s.diffWaysToCompute("2*3-4*5")); // expected: [-34, -10, -14, -10, 10]
    }
}
