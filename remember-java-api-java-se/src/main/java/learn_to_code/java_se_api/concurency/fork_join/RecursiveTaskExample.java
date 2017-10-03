package learn_to_code.java_se_api.concurency.fork_join;

import java.util.concurrent.RecursiveTask;

/**
 * This example creates a recursive task to count all values in array in parallel fashion.
 */
public class RecursiveTaskExample {
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

        int start;
        int end;
        int[] array;
        int threshold = 1000;

        public SumThis(int start, int end, int[] array) {
            this.start = start;
            this.end = end;
            this.array = array;
        }

        @Override
        protected Integer compute() {
            int sum = 0;

            /* If task is small enough to be executed in a single thread - then execute. Note that threshold value here is taken from
            * out from thin air. Usually you should study your task behavior to determine this value correctly */
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
