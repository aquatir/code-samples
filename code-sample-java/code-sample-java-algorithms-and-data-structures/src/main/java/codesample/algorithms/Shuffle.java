package codesample.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shuffle {

    private Shuffle() {
        // no creation
    }

    private static final Random RND = new Random();

    /**
     * Shuffle a list between indexes {@code [left; right)} to make it random
     */
    public static <T extends Comparable<T>> List<T> shuffle(List<T> list, int left, int right) {
        for (int i = left; i < right; i++) {
            // important to pick the swapped index as bound by i+1, instead of right, because
            // with right the list won't be uniformly shuffled by the end of the shuffle
            var swapWith = RND.nextInt(left, i + 1);
            swap(list, i, swapWith);
        }
        return list;
    }

    private static <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
        var tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    public static void main(String[] args) {
        var list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

        Shuffle.shuffle(list, 0, list.size());

        System.out.println(list);
    }
}
