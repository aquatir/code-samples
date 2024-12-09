package codesample.leetcode.medium;

/**
 * 1208. Get Equal Substrings Within Budget https://leetcode.com/problems/get-equal-substrings-within-budget/description/
 */
public class _1208_GetEqualSubstringsWithinBudget {
    public int equalSubstring(String s, String t, int maxCost) {

        // s.length() == t.length()
        // only lowercase letters;

        // sliding window with maxCost as boundary condition
        var answer = 0;
        var curCost = 0;
        var left = 0;
        for (int right = 0; right < s.length(); right++) {
            // expand
            curCost += cost(s.charAt(right), t.charAt(right));

            // shrink
            while (curCost > maxCost) {
                curCost -= cost(s.charAt(left), t.charAt(left));
                left++;
            }

            // recalculate
            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }

    private int cost(char one, char two) {
        return Math.abs(one - two);
    }
}

