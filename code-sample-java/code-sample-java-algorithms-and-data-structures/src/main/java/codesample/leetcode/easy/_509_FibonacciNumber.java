package codesample.leetcode.easy;

/** 509. Fibonacci Number — https://leetcode.com/problems/fibonacci-number/
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number
 * is the sum of the two preceding ones, starting from 0 and 1. That is,
 */
public class _509_FibonacciNumber {

    // iterative, sort of DP
//    public int fib(int n) {
//        if (n <= 0) return 0;
//        if (n <= 2) return 1;
//        int veryPrev = 1;
//        int prev = 1;
//        int cur = 0;
//        for (int i = 3; i <= n; i++) {
//            cur = veryPrev + prev;
//            veryPrev = prev;
//            prev = cur;
//        }
//
//        return cur;
//    }

    // Recursive
    public int fib(int n) {

        // +2 to handle (n=0) case
        var memo = new int[n+2];
        memo[0] = 0;
        memo[1] = 1;

        return fib(n, memo);
    }

    private int fib(int n, int[] memo) {
        if (n == 0 || memo[n] != 0) {
            return memo[n];
        }

        int res = fib(n - 1, memo) + fib(n - 2, memo);
        memo[n] = res;

        return res;
    }


    public static void main(String[] args) {
        final var fib = new _509_FibonacciNumber();
        System.out.println(fib.fib(2));
        System.out.println(fib.fib(3));
        System.out.println(fib.fib(4));
    }
}
