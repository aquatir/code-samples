package codesample.leetcode.medium;

import java.util.HashMap;

/**
 * 1248. Count Number of Nice Subarrays — https://leetcode.com/problems/count-number-of-nice-subarrays/description/
 */
public class _1248_CountNumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        // count number of odd elements
        // for each new elements:
        // if it's even: add the number of previously encountered odd subarrrays
        // if it's odd: do nothing
        var cur = 0;
        var ans = 0;

        // map number-of-odd-elements to number of subarrays that were encountered
        var freq = new HashMap<Integer, Integer>();
        freq.put(0, 1);

        // for example [2,2,2,1,2,2,1,2,2,2]
        // we will be increasing the number of subarrays with 0 odd numbers in them before we hit first 1 (4 times)
        // then we will increase the number of subarrays with 1 odd number in them before we hit second 1 (another 4 times)
        // when we hit second 1, we would know that there are 4 subarrays with 1 odd number, and we have just hit another one,
        // so it's ans += 4
        // then for each next even elements, we know that there are 4 subarrays with 2 odd numbers, so we keep incrementing
        // on each step by 4
        for (int i = 0; i < nums.length; i++) {
            cur += nums[i] % 2; // will add 1 for odd, and 0 for even numbers
            ans += freq.getOrDefault(cur - k, 0);
            freq.put(cur, freq.getOrDefault(cur, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        var s = new _1248_CountNumberOfNiceSubarrays();

        System.out.println(s.numberOfSubarrays(new int[]{2,2,2,1,2,2,1,2,2,2}, 2));
        // all 16 combinations
        // --------------------------
        // 1: 2,2,2,[1,2,2,1],2,2,2
        // 2: 2,2,2,[1,2,2,1,2],2,2
        // 3: 2,2,2,[1,2,2,1,2,2],2
        // 4: 2,2,2,[1,2,2,1,2,2,2]
        // 5: 2,2,[2,1,2,2,1],2,2,2
        // 6: 2,[2,2,1,2,2,1],2,2,2
        // 7: [2,2,2,1,2,2,1],2,2,2
        // 8: 2,2,[2,1,2,2,1,2],2,2
        // 9: 2,2,[2,1,2,2,1,2,2],2
        //10: 2,2,[2,1,2,2,1,2,2,2]
        //11: 2,[2,2,1,2,2,1,2],2,2
        //12: [2,2,2,1,2,2,1,2],2,2
        //13: 2,[2,2,1,2,2,1,2,2],2
        //14: 2,[2,2,1,2,2,1,2,2,2]
        //15: [2,2,2,1,2,2,1,2,2],2
        //16: [2,2,2,1,2,2,1,2,2,2]
    }
}
