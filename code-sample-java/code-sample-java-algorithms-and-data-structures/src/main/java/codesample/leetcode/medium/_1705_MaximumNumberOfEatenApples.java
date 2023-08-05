package codesample.leetcode.medium;

import java.util.PriorityQueue;

/**
 * 1705. Maximum Number of Eaten Apples — https://leetcode.com/problems/maximum-number-of-eaten-apples/
 */
public class _1705_MaximumNumberOfEatenApples {

    // I can use index-array magic!
    // class ApplesAndDays() {
    //     private int apples;
    //     private int days;

    //     public ApplesAndDays(int apples, int days) {
    //         this.apples = apples;
    //         this.days = days;
    //     }

    //     @Override
    //     public boolean equals(Object other) {
    //         ApplesAndDays o = (ApplesAndDays) other;
    //         return o.apples == this.apples
    //             && o.days == this.days
    //     }
    // }

    public int eatenApples(int[] apples, int[] days) {

        // pq of Integer of indexes
        // this should extract the index of apple that will rot the soonest
        var pq = new PriorityQueue<Integer>( (indexA, indexB) -> indexA + days[indexA] > indexB + days[indexB] ? indexA : indexB);

        // we will peek & decrement the numbre of apples in [apples] until it's zero. Then we will remove the index completely

        var day = 0;
        while (true) {
            // put the new apples to the list of apples
            if (day < apples.length) {
                pq.offer(day);
            }
            if (pq.isEmpty()) {
                break;
            }

            // peek for index apple that is about to rot
            // decrement number of those apples, increment the day
            // if the number of apples for index hits 0, remove
            // the index completely
            var index = pq.peek();
            apples[index]--;
            day++;
            if (apples[index] == 0) {
                pq.remove(index);
            }
        }
        return day;
    }

    public static void main(String[] args) {
        var s = new _1705_MaximumNumberOfEatenApples();
        s.eatenApples(new int[]{1,2,3,5,2}, new int[]{3,2,1,4,2}); // expected 7
    }
}
