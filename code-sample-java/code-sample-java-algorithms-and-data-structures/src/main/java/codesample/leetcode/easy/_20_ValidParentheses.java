package codesample.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class _20_ValidParentheses {
    public boolean isValid(String s) {

        // push only open bracket
        // when bracket is closed -> check is it's closing the correct opening bracket with pop. Otherwise -> false
        // return true if stack is empty at the end

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {

            char a = s.charAt(i);


            if (a == '(' || a == '{' || a == '[') {
                stack.push(a);
            } else if (stack.isEmpty()) return false;
            else if (a == ')' && stack.pop() != '(') return false;
            else if (a == ']' && stack.pop() != '[') return false;
            else if (a == '}' && stack.pop() != '{') return false;
        }
        return stack.isEmpty();
    }
}
