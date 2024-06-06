package codesample.other.crackingthecodinginterview.chapter1;

import java.util.HashSet;
import java.util.function.Function;

/*
Implement an algorithm to determine if a string has all unique characters. What if you can not use additional data structures?
 */
public class _0011_IsUnique {

    // tests:
    // abca: false
    // abc: true
    // abcdef: true
    // aa: false

    // with HashSet
    public boolean isUniqueHashSet(String str) {
        // create HashSet of characters.
        // iterate over chars
        // if char already is in the HashSet => false
        // if at the end of the string and never existed => true


        var hashSet = new HashSet<Character>();

        var characters = str.toCharArray();

        for (var ch : characters) {
            if (hashSet.contains(ch)) {
                return false;
            } else {
                hashSet.add(ch);
            }
        }

        return true;
    }

    public boolean isUniqueHitArray(String str) {
        // assume only lowercase, approach is extendable to upper too
        // there are 26 characters. Can create an array of 26 entries where 'a' = index 0, 'b' = index 1
        // change 0 to 1, when character is encountered. If 1 already false.

        var arr = new int[26];
        var chars = str.toCharArray();

        for (var ch: chars) {
            var index = ch - 'a';

            if (arr[index] == 1) {
                return false;
            } else {
                arr[index] += 1;
            }
        }

        return true;
    }

    public boolean isUniqueHitBitMask(String str) {
        // same as above, but instead of array of integer, just use a single long
        long bitmask = 0L;
        var chars = str.toCharArray();

        for (var ch: chars) {

            var bitPlace = ch - 'a';
            var bitAtPlaceAsDecimal = (long) Math.pow(2, bitPlace); // this will product 1, 2, 4, 8, 16 as values, corresponding to bits in a bitmask

            if ((bitmask & bitAtPlaceAsDecimal) != 0) {
                return false;
            } else {
                bitmask += bitAtPlaceAsDecimal; // after this operation, the next check in if will return false
            }
        }
        return true;
    }

    public static void main(String[] args) {
        var s = new _0011_IsUnique();

//        Function<String, Boolean> func = s::isUniqueHashSet;
//        Function<String, Boolean> func = s::isUniqueHitArray;
        Function<String, Boolean> func = s::isUniqueHitBitMask;

        System.out.println(func.apply("aa")); // exp: false
        System.out.println(func.apply("abca")); // exp: false
        System.out.println(func.apply("abc")); // exp: true
        System.out.println(func.apply("abcdef")); // exp: true
        System.out.println(func.apply("aa")); // exp: false
        System.out.println(func.apply("abcdefghijklmnopqrstuvwxyz")); // exp: true
    }
}
