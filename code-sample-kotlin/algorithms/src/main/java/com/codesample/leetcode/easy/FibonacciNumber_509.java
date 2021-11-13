package com.codesample.leetcode.easy;

/** 509. Fibonacci Number — https://leetcode.com/problems/fibonacci-number/
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number
 * is the sum of the two preceding ones, starting from 0 and 1. That is,
 */
public class FibonacciNumber_509 {
    public int fib(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return 1;
        int veryPrev = 1;
        int prev = 1;
        int cur = 0;
        for (int i = 3; i <= n; i++) {
            cur = veryPrev + prev;
            veryPrev = prev;
            prev = cur;
        }

        return cur;
    }

    public static void main(String[] args) {
        final var fib = new FibonacciNumber_509();
        System.out.println(fib.fib(3));
        System.out.println(fib.fib(2));
        System.out.println(fib.fib(4));
    }
}
