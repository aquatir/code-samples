package learn_to_code.java_se_api.concurency.collections;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue provides a powerful concurrency mechanism to synchronize work in different threads.
 * In order to take() element from queue a call to put() should be active from another thread. At the same time
 * in order to put() something in a queue, a call to take() should be pending from other thread.
 */
public class SynchronousQueueExample {
    public static void main(String args[]) throws IOException, InterruptedException {

        SynchronousQueue<String> str = new SynchronousQueue<>();

        Thread tr = new Thread(new MyRunnable(str));
        tr.start();
        str.put("kek");

        System.out.println("Finished");
    }

    private static class MyRunnable implements Runnable {

        SynchronousQueue<String> queue;

        public MyRunnable(SynchronousQueue<String> queue) {
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
}
