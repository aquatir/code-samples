package learn_to_code.javaSE_api.concurency.synchronization;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * An example of CyclicBarrier usage.
 * First we create a cyclic barrier which will be waiting for 3 threads to get to the barrier.
 * When each of this threads calls await(), the barrier will be broken and all 3 threads will execute
 * their last line of code (Print out 'await finished!')
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        int numOfThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numOfThreads);

        System.out.println("Threads are gathering on barrier");

        Thread thread1 = new Thread(new BarrierAwaiter(barrier, 1000, "Thread 1"));
        Thread thread2 = new Thread(new BarrierAwaiter(barrier, 2000, "Thread 2"));
        Thread thread3 = new Thread(new BarrierAwaiter(barrier, 4000, "Thread 3"));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class BarrierAwaiter implements Runnable {

    private CyclicBarrier barrier;
    private int waitTime;
    private String threadName;

    BarrierAwaiter(CyclicBarrier barrier, int waitTime, String threadName) {
        this.barrier = barrier;
        this.waitTime = waitTime;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(threadName + " started");
        try {
            Thread.sleep(waitTime);
            System.out.println(threadName + " awaiting barrier");
            barrier.await();
            System.out.println(threadName + " awaiting finished!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
