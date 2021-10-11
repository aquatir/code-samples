package com.codesample.leetcode.medium

fun main() {
    val s = Solution1()

    println(s.decodeString("3[a]2[bc]")) // "aaabcbc"
    println(s.decodeString("3[a2[c]]")) // "accaccacc"
    println(s.decodeString("2[abc]3[cd]ef")) // "abcabccdcdcdef"
    println(s.decodeString("abc3[cd]xyz")) // "abccdcdcdxyz"
}

/** 394. Decode String
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * */
class Solution1 {
    fun decodeString(s: String): String {

        val finalResultStringBuilder = StringBuilder()
        var currentStringBuilder = StringBuilder()
        var currentNumberOfRepeats = 0
        var i = 0

        while (i < s.length) {
            val char = s[i]

            if (char == ']') {
                // skip -> nothing to process
            } else if (char == '[') {
                val endOfRepeat = findCorrespondingClosingBracketFrom(s, i + 1)
                val leftOfString = decodeString(s.substring(i + 1, endOfRepeat))
                for (j in 1..currentNumberOfRepeats) {
                    finalResultStringBuilder.append(leftOfString)
                }
                currentNumberOfRepeats = 0
                i = endOfRepeat // this will be traversed by 1 at the end of the while
            } else if (char.isLetter()) {
                currentStringBuilder.append(char)
            } else if (char.isDigit()) {
                finalResultStringBuilder.append(currentStringBuilder)
                currentStringBuilder = StringBuilder()

                if (currentNumberOfRepeats != 0) {
                    currentNumberOfRepeats = currentNumberOfRepeats * 10 + (char.toInt() - '0'.toInt())
                } else {
                    currentNumberOfRepeats = char.toInt() - '0'.toInt()
                }
            }
            i++
        }

        return finalResultStringBuilder.append(currentStringBuilder).toString()
    }

    /** if another opening bracket is met along the way, we should continue until we close both of them.
     * i will point to ']' at the end of function */
    private fun findCorrespondingClosingBracketFrom(s: String, startIndex: Int): Int {
        var closedBracketsToFind = 1;

        for (i in startIndex..s.length - 1) {
            val char = s[i]
            if (char == '[') {
                closedBracketsToFind++
            }
            if (char == ']') {
                closedBracketsToFind--
            }

            if (closedBracketsToFind == 0) {
                return i
            }
        }
        return 0 // never reached
    }
}
