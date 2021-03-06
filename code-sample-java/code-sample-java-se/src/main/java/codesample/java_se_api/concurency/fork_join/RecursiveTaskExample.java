package codesample.java_se_api.concurency.fork_join;

import java.util.concurrent.RecursiveTask;

/**
 * This example creates a recursive task to count all values in array in parallel fashion.
 */
class RecursiveTaskExample {
    public static void main(String[] args) {
        int[] sumThisValues = new int[100000];
        for (int i = 0; i < sumThisValues.length; i++) {
            sumThisValues[i] = i;
        }

        SumThis task = new SumThis(0, sumThisValues.length, sumThisValues);
        task.invoke();

        System.out.println("Sum from 0 to " + (sumThisValues.length - 1) + " is " + task.join() );
    }

    static class SumThis extends RecursiveTask<Integer> {

        final int start;
        final int end;
        final int[] array;
        final int threshold = 1000;

        SumThis(int start, int end, int[] array) {
            this.start = start;
            this.end = end;
            this.array = array;
        }

        @Override
        protected Integer compute() {
            int sum = 0;

            /* If task is small enough to be executed in a single thread - then execute. Note that threshold value here
             * is taken as magic number. Usually you should test and profile your task behavior to determine this value
             * correctly to achieve the best performance and memory footprint */
            if ((end - start) < threshold) {
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
            } else {
                int middle = (start + end) / 2;
                SumThis subTaskMin = new SumThis(start, middle, array);
                SumThis subTaskMax = new SumThis(middle, end, array);

                /* Calling fork() will return imminently. */
                subTaskMin.fork();
                subTaskMax.fork();

                /* Calling join() will block until computation is complete in subtask */
                sum = subTaskMin.join() + subTaskMax.join();

            }

            return sum;
        }
    }
}
