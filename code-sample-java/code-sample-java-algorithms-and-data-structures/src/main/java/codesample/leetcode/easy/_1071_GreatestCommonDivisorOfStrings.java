package codesample.leetcode.easy;

/**
 * 1071. Greatest Common Divisor of Strings — https://leetcode.com/problems/greatest-common-divisor-of-strings/?envType=study-plan-v2&envId=leetcode-75
 */
public class _1071_GreatestCommonDivisorOfStrings {

    private int len1;
    private int len2;

    public String gcdOfStrings(String str1, String str2) {
        len1 = str1.length();
        len2 = str2.length();

        for (int i = Math.min(len1, len2); i >= 1; --i) {
            if (valid(str1, str2, i)) {
                return str1.substring(0, i);
            }
        }
        return "";
    }

    public boolean valid(String str1, String str2, int k) {
        if (len1 % k > 0 || len2 % k > 0) {
            return false;
        } else {
            String base = str1.substring(0, k);
            return str1.replace(base, "").isEmpty() && str2.replace(base, "").isEmpty();
        }
    }

    public static void main(String[] args) {
        var s = new _1071_GreatestCommonDivisorOfStrings();

        System.out.println(s.gcdOfStrings("ABABAB", "ABAB")); // expected: "AB"
        System.out.println(s.gcdOfStrings("LEET", "CODE")); // expected: ""
        System.out.println(s.gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX")); // expected: "TAUXX"
    }
}
