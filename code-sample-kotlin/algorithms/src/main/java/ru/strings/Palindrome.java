package ru.strings;

public class Palindrome {

    public boolean isPalindromeLine(String str) {
        var sb = new StringBuilder(str.toLowerCase());

        var index = 0;
        while (index < sb.length()) {
            if (sb.charAt(index) == ' ') {
                sb.deleteCharAt(index);
            }
            index++;
        }

        var sbReversed = (new StringBuilder(sb)).reverse();
        return sbReversed.toString().equals(sb.toString());
    }

    public static void main(String[] args) {
        var p = new Palindrome();
        System.out.println(p.isPalindromeLine("Молебен о коне белом"));
        System.out.println(p.isPalindromeLine("А роза упала на лапу Азора"));
    }
}
