package codesample.java_se_api.concurency.collections.queue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * LinkedTransferQueueExample example
 *
 * LinkedTransferQueueExample is a special concurrent queue which can allow you to build complex queueing mechanisms.
 * Various methods of this queue will or will not put elements in queue depending whatever or not a consumer for this queue
 * exists. The same goes with queering elements from this queue.
 */
public class LinkedTransferQueueExample {
    public static void main(String[] args) throws InterruptedException {

        var queue = new LinkedTransferQueue<Integer>();

        var putter = new Thread(new PutterTask(queue));
        var getter = new Thread(new GetterTask(queue));

        putter.start();
        getter.start();

        putter.join();
        getter.join();
    }

    private static class PutterTask implements Runnable {

        LinkedTransferQueue<Integer> queue;

        public PutterTask(LinkedTransferQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("try-inserting 10000 elements without consumer");
                for (var i = 0; i < 10000; i++) {
                    /* Try transfer literelly tryes to transfer the element if consumer is currently waiting.
                    If nobody is waiting the element WILL NOT be put into queue.
                    */
                    queue.tryTransfer(i);
                }

                System.out.println("Current queue size: " + queue.size());
                System.out.println("Turning producer down for 2000 mils");
                Thread.sleep(2000);

                System.out.println("Producer is up!");
                for (var i = 0; i < 10000; i++) {

                    /* Transfer blocks until consumer comes online */
                    queue.transfer(i);
                    System.out.println("Producer transfering " + i);
                }

                System.out.println("Producer finished");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static class GetterTask implements Runnable {

        LinkedTransferQueue<Integer> queue;

        public GetterTask(LinkedTransferQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("Consumer is offline for 2000 mils" );
                Thread.sleep(2000);

                System.out.println("Consumer is online");
                Integer totalSum = 0;
                for (var i = 0; i < 10000; i++) {

                    /* Take will block until the element exists.
                    * If you don't want to wait, you can use .poll() instead  */
                    var tookValue = queue.take();
                    System.out.println("Consumer took value " + tookValue);
                    totalSum += tookValue;
                }

                System.out.println("Consumer finished. Total sum: " + totalSum);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
