package codesample.java_se_api.concurency.collections;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue example
 *
 * PriorityBlockingQueue is a queue with elements which can be dequeued by priority.
 */
public class PriorityBlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        var queue = new PriorityBlockingQueue<Integer>();

        var putter_1 = new Thread(new PutterTask(queue));
        var putter_2 = new Thread(new PutterTask(queue));
        var putter_3 = new Thread(new PutterTask(queue));

        putter_1.start();
        putter_2.start();
        putter_3.start();

        putter_1.join();
        putter_2.join();
        putter_3.join();

        for (var i = 0 ; i < queue.size(); i++) {
            System.out.println(queue.poll());
        }
    }

    private static class PutterTask implements Runnable {

        PriorityBlockingQueue<Integer> queue;
        Random rnd = new Random(0);

        public PutterTask(PriorityBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (var i = 0; i < 100000; i++) {
                queue.offer(rnd.nextInt(100000));
            }
        }
    }
}
