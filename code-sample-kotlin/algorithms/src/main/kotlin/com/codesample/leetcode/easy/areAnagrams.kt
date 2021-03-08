package com.codesample.leetcode.easy

fun areAnagrams(a: String, b: String): Boolean {

    fun strToMap(str: String): Map<Char, Int> {
        val map = mutableMapOf<Char, Int>()
        for (i in str.indices) {
            val char = str[i]
            if (map.containsKey(char)) {
                map[char] = map.getValue(char) + 1
            } else {
                map[char] = 1
            }
        }
        return map
    }

    val aMap = strToMap(a)
    val bMap = strToMap(b)

    return aMap == bMap
}

fun main() {
    println(areAnagrams("abc", "bca")) // true
    println(areAnagrams("abc", "abc")) // true
    println(areAnagrams("abc", "baac")) // false
    println(areAnagrams("baac", "abc")) // false
    println(areAnagrams("", "")) // true
}
