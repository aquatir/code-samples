package codesample.leetcode.medium;

/**
 * 50. Pow(x, n) — https://leetcode.com/problems/powx-n/description/
 */
public class _50_Pow_x_n {

    // fast power algorithm
    // multiple power by bits. If bit is present -> multiple, if not -> don't multiple.
    // the current power grows in curProd var
    public double myPow(double x, int n) {
        long initial = n;
        if (initial < 0) {
            x = 1 / x;
            initial = -initial;
        }
        double ans = 1;
        double curProd = x;
        for (long i = initial; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * curProd;
            }
            curProd = curProd * curProd;
        }
        return ans;
    }
}
