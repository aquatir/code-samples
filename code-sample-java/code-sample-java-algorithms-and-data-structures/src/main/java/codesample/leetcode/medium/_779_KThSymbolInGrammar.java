package codesample.leetcode.medium;

/**
 * 779. K-th Symbol in Grammar — https://leetcode.com/problems/k-th-symbol-in-grammar/description/
 */
public class _779_KThSymbolInGrammar {
    static class Solution {
        public int kthGrammar(int n, int k) {

            // see the problem as a tree
            //row1. |        0
            //row2. |    0      1
            //row3. |  0   1   1   1
            //row4. | 0 1 1 0 1 0 0 1
            // ------------------------
            //index   1 2 3 4 5 6 7 8
            // Here it becomes obvious that the parent of Kth index in Nth row is
            // either k/2 index when (n-1) is even
            // (k+1)/2 index when (n-1) is odd
            if (n == 1 && k == 1) {
                return 0;
            }

            if( k % 2 == 1) {
                return kthGrammar(n-1, (k+1)/2);
            } else {
                return kthGrammar(n-1, k/2) == 0 ? 1 : 0;
            }
        }
    }
}
