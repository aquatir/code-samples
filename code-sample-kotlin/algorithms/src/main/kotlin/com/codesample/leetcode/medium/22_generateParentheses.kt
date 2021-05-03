package com.codesample.leetcode.medium

import java.lang.StringBuilder

/** 22. Generate Parentheses https://leetcode.com/problems/generate-parentheses/
 *
 * Given n, output every correct parenthesis sequence with size 2*n. Input should be sorted lexicographically
 * e.g:
 * n = 1: ()
 * n = 2: (()),()()
 * n = 3: ((())),(()()),(())(),()(()),()()()
 * */

fun generateParenthesis(n: Int): List<String> {

    val opened = 1
    val good = mutableListOf<String>();

    fun allGoodParenthesis(opened: Int, parenthesisLeft: Int, current: String) {
        if (parenthesisLeft == 0) {
            good.add(current)
            return
        }
        if (opened > 0 && parenthesisLeft > opened) { // some opened, but can open more
            allGoodParenthesis(opened + 1, parenthesisLeft - 1, "$current(")
        }
        if (opened == 0 && parenthesisLeft >= 2) { // none opened, but can open + close
            allGoodParenthesis(opened + 1, parenthesisLeft - 1, "$current(")
        }
        if (opened > 0) { // close opened
            allGoodParenthesis(opened - 1, parenthesisLeft - 1, "$current)")
        }

    }

    allGoodParenthesis(opened, n*2 - 1, "(")
    return good.map { "\"$it\"" }
}

fun main() {
    println(generateParenthesis(1)) // ()
    println(generateParenthesis(2)) // (()),()()
    println(generateParenthesis(3)) // ((())),(()()),(())(),()(()),()()()
}
