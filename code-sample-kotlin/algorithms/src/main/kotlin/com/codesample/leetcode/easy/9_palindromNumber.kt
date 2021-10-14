package com.codesample.leetcode.easy


fun main() {
    val s = Solution2()

    println(s.isPalindromeNoConvertToString( -101)) // false
    println(s.isPalindromeNoConvertToString(121))   // true
    println(s.isPalindromeNoConvertToString(123))   // false
}

/** 9. Palindrome Number — https://leetcode.com/problems/palindrome-number/
 * Given an integer x, return true if x is palindrome integer.
 * An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.
 */
class Solution2 {
    fun isPalindrome(x: Int): Boolean {
        if (x < 0) return false

        val y = x.toString()
        return y.reversed() == y
    }

    fun isPalindromeNoConvertToString(x: Int): Boolean {
        if (x < 0) return false
        if (x < 10) return true
        var y = x
        var reverted = 0

        while (y != 0) {
            reverted = reverted * 10 + y % 10
            y = y / 10
        }

        return reverted == x
    }
}
