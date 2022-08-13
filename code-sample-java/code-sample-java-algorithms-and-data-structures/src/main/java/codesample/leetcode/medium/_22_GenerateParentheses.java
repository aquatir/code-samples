package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses — https://leetcode.com/problems/generate-parentheses/
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 */
public class _22_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        int opened = 1;
        List<String> good = new ArrayList<>();

        allGoodParenthesis(opened, n * 2 - 1, "(", good);

        return good;
    }


    public void allGoodParenthesis(int opened, int parenthesisLeft, String current, List<String> result) {
        if (parenthesisLeft == 0) {
            result.add(current);
            return;
        }

        if (opened > 0 && parenthesisLeft > opened) { // some opened, but can open more
            allGoodParenthesis(opened + 1, parenthesisLeft - 1, current + "(", result);
        }
        if (opened == 0 && parenthesisLeft >= 2) { // none opened, but can open + close
            allGoodParenthesis(opened + 1, parenthesisLeft - 1, current + "(", result);
        }
        if (opened > 0) { // close opened
            allGoodParenthesis(opened - 1, parenthesisLeft - 1, current + ")", result);
        }
    }
}
