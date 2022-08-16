package codesample.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;
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
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> numbersBeforeLastOpenBracketStack = new ArrayDeque<>();

        int curNumber = 0;

        // this will remember if the last value is positive or not
        boolean isNextPositive = true;

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur == ' ') {
                continue;
            }
            if (Character.isDigit(cur)) {
                curNumber = curNumber * 10 + (cur - '0');
            }
            if (cur == '+' || cur == '-') {
                int toPush = (isNextPositive ? curNumber : -curNumber);
                stack.push(toPush);
                curNumber = 0;
                isNextPositive = cur == '+';
            }
            if (cur == '(') {
                if (isNextPositive) {
                    numbersBeforeLastOpenBracketStack.push(stack.size());
                } else {
                    numbersBeforeLastOpenBracketStack.push(-stack.size());
                }
                isNextPositive = true;
            }
            if (cur == ')') {
                int toPush = (isNextPositive ? curNumber : -curNumber);
                stack.push(toPush);
                curNumber = 0;

                int numbersBeforeLastOpenBracket = numbersBeforeLastOpenBracketStack.pop();
                isNextPositive = numbersBeforeLastOpenBracket >= 0;
                numbersBeforeLastOpenBracket = Math.abs(numbersBeforeLastOpenBracket);

                // this will pop out only the values in the brackets
                while (stack.size() > numbersBeforeLastOpenBracket) {
                    curNumber += stack.pop();
                }
            }

        }

        int toPush = isNextPositive ? curNumber : -curNumber;
        stack.push(toPush);

        int result = 0;
        for (int temp : stack) {
            result += temp;
        }

        return result;
    }
}
