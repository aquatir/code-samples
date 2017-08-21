package learn_to_code.javaSE_api.concurency.synchronization;


import java.util.concurrent.CountDownLatch;

/**
 * An example of CountDownLatch usage.
 * The main thread in this example will create a latch and a count down thread, which will count down this latch every second.
 * When the count down thread finish the main thread will continue on and print out last message to console
 */
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {

        int numOfCountDowns = 5;
        CountDownLatch latch = new CountDownLatch(numOfCountDowns);

        System.out.println("Main thread is waiting for countdown latch");
        Thread countDowner = new Thread(new CountDownRunnable(latch, numOfCountDowns));
        countDowner.start();

        /* main thread will wait on this line of code until count down thread will
        * count to 5 */
        latch.await();
        System.out.println("Waiting complete. Proceeding!");

    }
}

class CountDownRunnable implements Runnable {

    private CountDownLatch latch;
    private int numOfCountDowns;

    public CountDownRunnable(CountDownLatch latch, int numOfCountDowns) {
        this.latch = latch;
        this.numOfCountDowns = numOfCountDowns;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < numOfCountDowns; i++) {
                System.out.println("Thread counting down latch " + i);
                latch.countDown();
                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
