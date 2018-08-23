package codesample.java_se_api.concurency.collections;

import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue provides a powerful concurrency mechanism to synchronize work in different threads.
 * In order to take() element from queue a call to put() should be active from another thread. At the same time
 * in order to put() something in a queue, a call to take() should be pending from other thread.
 */
class SynchronousQueueExample {
    public static void main(String args[]) throws InterruptedException {

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread taker = new Thread(new SynchronousQueueTaker(queue));
        taker.start();

        System.out.println("Nothing in output, because taker thread has nothing to take thus it's blocked");

        Thread putter = new Thread(new SynchronousQueuePutter(queue, "Value passed to putter!"));
        putter.start();

        /* Wait for both threads before outputting finish*/
        taker.join();
        putter.join();
        System.out.println("Finished");

    }

    private static class SynchronousQueueTaker implements Runnable {

        final SynchronousQueue<String> queue;

        SynchronousQueueTaker(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static class SynchronousQueuePutter implements Runnable {

        final SynchronousQueue<String> queue;
        final String value;

        SynchronousQueuePutter(SynchronousQueue<String> queue, String value) {
            this.queue = queue;
            this.value = value;
        }

        @Override
        public void run() {
            try {
                queue.put(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
