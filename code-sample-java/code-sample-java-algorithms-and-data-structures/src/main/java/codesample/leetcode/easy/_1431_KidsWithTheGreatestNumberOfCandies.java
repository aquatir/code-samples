package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 1431. Kids With the Greatest Number of Candies — https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/description/
 */
public class _1431_KidsWithTheGreatestNumberOfCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        var res = new ArrayList<Boolean>();
        // find max in array
        // now check if i + extra >= max => true, else false

        var max = 0;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }

        for (int candy : candies) {
            res.add(candy + extraCandies >= max);
//            if (candy + extraCandies >= max) {
//                res.add(true);
//            } else {
//                res.add(false);
//            }
        }

        return res;
    }
}

