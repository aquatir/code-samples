package codesample.java_se_api.concurency.fork_join;

import java.util.concurrent.RecursiveAction;

/**
 * Fork/Join framework provides an effective mechanism for recursive tasks which sometimes may be easier to use
 * that classical Executor framework. One of the advantages of Fork/Join framework is RecursiveAction/RecursiveTask classes.
 * They provide a simple way to split execution of any task recursively.
 *
 * This example creates a recursive action to double each value in array and put it back (which you SHOULD do with
 * stream api since java 8, but this is an example)
 */
class RecursiveActionExample {
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

        final int threshold = 1000;
        final int start;
        final int end;
        final int[] array;

        ArrayValueMultiplier(int start, int end, int[] array) {
            this.start = start;
            this.end = end;
            this.array = array;
        }

        @Override
        protected void compute() {
            /* If task is small enough to be executed in a single thread - then execute. Note that threshold value here
             * is taken as magic number. Usually you should test and profile your task behavior to determine this value
             * correctly to achieve the best performance and memory footprint */
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
