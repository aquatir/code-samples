package codesample.leetcode.medium;

/**
 * 779. K-th Symbol in Grammar — https://leetcode.com/problems/k-th-symbol-in-grammar/description/
 */
public class _779_KThSymbolInGrammar {
    static class Solution {
        public int kthGrammar(int n, int k) {

            // after applying the operation left half of array is exactly the same as right half
            // we can use recursion to either find the element in left half or right half
            if (n == 1 && k == 1) {
                return 0;
            }

            int mid = (int) (Math.pow(2, n-1)/2);

            if (k <= mid) {
                return kthGrammar(n-1, k);
            } else {
                return kthGrammar(n-1, k-mid) == 1 ? 0 : 1;
            }
        }
    }
}
