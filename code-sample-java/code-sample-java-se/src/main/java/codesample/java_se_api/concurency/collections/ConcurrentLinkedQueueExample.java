package codesample.java_se_api.concurency.collections;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue example
 *
 * ConcurrentLinkedQueue is a concurrent version of classical Queue.
 * This example puts 100000 LocalDateTime.now() object into queue in 3 threads simultaneously expecting them to be placed
 * in order (Order is not checked though)
 *
 * note: there also exists ConcurrentLinkedDeque, which is a DEQUE supporting both FIFO and LIFO at the same time.
 * Notice however that supporting both may have a significant performance penalty.
 */
public class ConcurrentLinkedQueueExample {
    public static void main(String[] args) throws InterruptedException {
        var queue = new ConcurrentLinkedQueue<LocalDateTime>();

        var putter1 = new Thread(new PutterTask(queue));
        var putter2 = new Thread(new PutterTask(queue));
        var putter3 = new Thread(new PutterTask(queue));

        putter1.run();
        putter2.run();
        putter3.run();

        putter1.join();
        putter2.join();
        putter3.join();

        queue.forEach(System.out::println);
        System.out.println("Total number of elements: " + queue.size());
    }

    private static class PutterTask implements Runnable {

        ConcurrentLinkedQueue<LocalDateTime> queue;
        Random rnd = new Random(0);

        public PutterTask(ConcurrentLinkedQueue<LocalDateTime> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (var i = 0; i < 100000; i++) {
                queue.add(LocalDateTime.now());
            }
        }
    }
}
