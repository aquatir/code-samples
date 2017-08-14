package learn_to_code.java_api.concurency.fork_join;

import java.util.concurrent.RecursiveAction;

/**
 * Fork/Join framework provides an effective mechanism for recursive tasks which sometimes may be easier to use
 * that classical Executor framework. One of the advantages of Fork/Join framework is RecursiveAction/RecursiveTask classes.
 * They provide a simple way to split execution of any task recursively.
 *
 * This example creates a recursive task to double each value in array and put it back
 */
public class RecursiveActionExample {
    public static void main(String[] args) {

        int printUntil = 41;
        int[] values = new int[10000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }

        for (int i = 0; i < printUntil; i++) {
            System.out.printf("%4d ", values[i]);
        }
        System.out.println();

        ArrayValueMultiplier multiplier = new ArrayValueMultiplier(0,values.length, values);
        multiplier.invoke();

        for (int i = 0; i < printUntil; i++) {
            System.out.printf("%4d ", values[i]);
        }
    }

    static class ArrayValueMultiplier extends RecursiveAction {

        int threshold = 1000;
        int start;
        int end;
        int[] array;

        public ArrayValueMultiplier(int start, int end, int[] array) {
            this.start = start;
            this.end = end;
            this.array = array;
        }

        @Override
        protected void compute() {
            /* If task is small enough to be executed in a single thread - then execute. Note that threshold value here is taken from
            * out from thin air. Usually you should study your task behavior to determine this value correctly */
            if ((end - start) < threshold) {
                for (int i = start; i < end; i++) {
                    array[i] = array[i] * array[i];
                }
            } else {
                int middle = (start + end) / 2;
                invokeAll(new ArrayValueMultiplier(start, middle, array),
                        new ArrayValueMultiplier(middle, end, array));
            }
        }
    }

}
