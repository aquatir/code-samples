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
        Stack<Integer> pStack = new Stack<Integer>();

        int num = 0;
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur == ' ') {
                continue;
            }
            if (Character.isDigit(cur)) {
                num = num * 10 + (cur - '0');
            }
            if (cur == '+' || cur == '-') {
                int toPush = (sign == '-' ? -num : num);
                stack.push(toPush);
                num = 0;
                sign = cur;
            }
            if (cur == '(') {
                if (sign == '+') {
                    pStack.push(stack.size());
                } else if (sign == '-') {
                    pStack.push(-stack.size());
                }
                sign = '+';
            }
            if (cur == ')') {
                int toPush = (sign == '-' ? -num : num);
                stack.push(toPush);
                num = 0;

                int numLeft = pStack.pop();
                sign = (numLeft < 0 ? '-' : '+');
                numLeft = Math.abs(numLeft);

                while (stack.size() > numLeft) { // this will pop out only the values in the brackets
                    num += stack.pop();
                }
            }

        }

        int toPush = (sign == '-' ? -num : num);
        stack.push(toPush);

        int result = 0;
        for (int temp : stack) {
            result += temp;
        }

        return result;
    }
}
