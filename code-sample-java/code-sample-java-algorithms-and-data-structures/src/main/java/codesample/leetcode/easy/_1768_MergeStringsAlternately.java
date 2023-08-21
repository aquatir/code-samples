package codesample.leetcode.easy;

/**
 * 1768. Merge Strings Alternately — https://leetcode.com/problems/merge-strings-alternately/description/?envType=study-plan-v2&envId=leetcode-75
 */
public class _1768_MergeStringsAlternately {
    public String mergeAlternately(String word1, String word2) {
        var sb = new StringBuilder();

        int left = 0;
        int lenLeft = word1.length();
        int right = 0;
        int lenRight = word2.length();

        boolean nextLeft = true;

        while (true) {
            if (left == lenLeft && right == lenRight) {
                break;
            }

            if (nextLeft) {
                if (left != lenLeft) {
                    sb.append(word1.charAt(left++));
                } else {
                    sb.append(word2.charAt(right++));
                }
            } else {
                if (right != lenRight) {
                    sb.append(word2.charAt(right++));
                } else {
                    sb.append(word1.charAt(left++));
                }
            }
            nextLeft = !nextLeft;
        }

        return sb.toString();
    }
}
