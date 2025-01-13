package example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class PqUniqueSort {

    public static class CustomInteger {

        private final Integer i;

        public CustomInteger(Integer i) {
            this.i = i;
        }

        public Integer getI() {
            return i;
        }

        public int hashCode() {
            return i; // handle null
        }

        public boolean equals(Object other) {
            // check correct class
            CustomInteger otherClass = (CustomInteger) other;
            return this.i.equals(otherClass.i);
        }

        public String toString() {
            return i.toString();
        }
    }

    public static void main(String[] args) {
        //System.out.println(uniqueSort(List.of(1, 4, 4, 4, 4, 4, 3, 2, 1, 2)));
        System.out.println(uniqueSort2(List.of(
            new CustomInteger(1),
            new CustomInteger(4),
            new CustomInteger(4),
            new CustomInteger(4),
            new CustomInteger(4),
            new CustomInteger(4),
            new CustomInteger(1),
            new CustomInteger(2),
            new CustomInteger(2),
            new CustomInteger(2)))
        );
    }

    private static List<CustomInteger> uniqueSort2(List<CustomInteger> arr) {


        var seen = new HashSet<CustomInteger>();
        var minQueue = new PriorityQueue<CustomInteger>(
            Comparator.comparingInt(a -> a.i)
        ); // default sort order correct?

        for (var element : arr) {
            // time complexity: HashSet.contains is O(1)
            if (!seen.contains(element)) {
                seen.add(element);
                minQueue.add(element);
            }
        }

        var result = new ArrayList<CustomInteger>();
        while (!minQueue.isEmpty()) {
            result.add(minQueue.poll());
        }
        return result;
    }
}
