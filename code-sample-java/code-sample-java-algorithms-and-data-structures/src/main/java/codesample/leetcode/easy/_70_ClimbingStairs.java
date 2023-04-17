package codesample.leetcode.easy;

/**
 * 70. Climbing Stairs — https://leetcode.com/problems/climbing-stairs/
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class _70_ClimbingStairs {

    /* The solution for S[n] is a sum of all the ways to get to the last step s[n-1] + all the ways to get to the
    * previous step s[n-2] so it's essentially a Fibonacci sequence. */
    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int oneStepWays = 1;
        int twoStepWays = 1;
        int cur = 0;

        for (int i = 2; i <= n; i++) {
            cur = oneStepWays + twoStepWays;
            twoStepWays = oneStepWays;
            oneStepWays = cur;
        }

        return cur;
    }

    // recursive solution
//    public int climbStairs(int n) {
//        return climbStairs(0, n, new int[n + 1]);
//    }
//
//    private int climbStairs(int cur, int n, int[] memo) {
//        if (cur > n) {
//            return 0;
//        }
//        if (cur == n) {
//            return 1;
//        }
//
//        if (memo[cur] != 0) {
//            return memo[cur];
//        } else {
//            var value = climbStairs(cur + 1, n, memo) + climbStairs(cur + 2, n, memo);
//            memo[cur] = value;
//            return memo[cur];
//        }
//    }
}
