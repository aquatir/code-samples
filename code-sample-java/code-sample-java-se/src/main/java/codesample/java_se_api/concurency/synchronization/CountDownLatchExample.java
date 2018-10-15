package codesample.java_se_api.concurency.synchronization;


import java.util.concurrent.CountDownLatch;

/**
 * An example of CountDownLatch usage.
 * The main thread in this example will create a latch and a 2 count down threads, which will count down this latch every second.
 * When the count down thread finish the main thread will continue on and print out last message to console
 *
 * Note that getCount and countDown methods of latch are synchronized internally, so we don't have to worry about calling
 * them
 */
class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {

        int numOfCountDowns = 6;
        CountDownLatch latch = new CountDownLatch(numOfCountDowns);

        System.out.println("Main thread is waiting for countdown latch");
        Thread countDowner1 = new Thread(new CountDownRunnable(latch));
        Thread countDowner2 = new Thread(new CountDownRunnable(latch));
        countDowner1.start();
        countDowner2.start();

        /* main thread will wait on this line of code until count down thread will
        * count to 6 */
        latch.await();
        System.out.println("Waiting complete. Proceeding!");

    }

    private static class CountDownRunnable implements Runnable {

        private final CountDownLatch latch;

        public CountDownRunnable(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            int i = 0;

            try {
                while (latch.getCount() != 0) {
                    System.out.println("Thread counting down latch " + (i + 1));
                    latch.countDown();
                    i++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


