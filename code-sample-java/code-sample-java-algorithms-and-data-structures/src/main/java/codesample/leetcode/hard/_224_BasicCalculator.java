package codesample.leetcode.hard;

import java.util.Stack;

/**
 * 224. Basic Calculator — https://leetcode.com/problems/basic-calculator/
 * <p>
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * <p>
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 */
public class _224_BasicCalculator {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> numbersBeforeLastOpenBracketStack = new Stack<Integer>();

        int num = 0;
        boolean isPositive = true;

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur == ' ') {
                continue;
            }
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            }
            if (cur == '+' || cur == '-') {
                int toPush = (isPositive ? num : -num);
                stack.push(toPush);
                num = 0;
                isPositive = cur == '+';
            }
            if (cur == '(') {
                if (isPositive) {
                    numbersBeforeLastOpenBracketStack.push(stack.size());
                } else {
                    numbersBeforeLastOpenBracketStack.push(-stack.size());
                }
                isPositive = true;
            }
            if (cur == ')') {
                int toPush = (isPositive ? num : -num);
                stack.push(toPush);
                num = 0;

                int numbersBeforeLastOpenBracket = numbersBeforeLastOpenBracketStack.pop();
                isPositive = numbersBeforeLastOpenBracket >= 0;
                numbersBeforeLastOpenBracket = Math.abs(numbersBeforeLastOpenBracket);

                // this will pop out only the values in the brackets
                while (stack.size() > numbersBeforeLastOpenBracket) {
                    num += stack.pop();
                }
            }

        }

        int toPush = isPositive ? num : -num;
        stack.push(toPush);

        int result = 0;
        for (int temp : stack) {
            result += temp;
        }

        return result;
    }
}
