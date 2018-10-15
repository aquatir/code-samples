package codesample.java_se_api.concurency.synchronization;

import java.util.concurrent.Semaphore;

/**
 * An example of Semaphore class usage.
 * We create a semaphore with a single permit. Then launch 2 threads. One should increment a shared area value by 5
 * (by 1 on each iteration), another should decrement this value by 5 (by 1 with each iteration).
 *
 * We can see, that 2 threads are starting simultaneously, but they can not acquire semaphore at the same time
 * Also note, that if you increase number of semaphore permits, both threads will work at the same time
 */
class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        int numOfIterations = 5;

        Thread inc = new Thread(new Incrementer(sem, numOfIterations));
        Thread dec = new Thread(new Decrementer(sem, numOfIterations));

        inc.start();
        dec.start();
    }

    private static class SharedArea {
        public static volatile int value = 0;
    }

    private static class Incrementer implements Runnable {
        private final Semaphore sem;
        private final int numOfIncrements;

        Incrementer(Semaphore sem, int numOfIncrements) {
            this.sem = sem;
            this.numOfIncrements = numOfIncrements;
        }

        @Override
        public void run() {
            System.out.println("Incrementer thread launched");
            try {
                sem.acquire();
                System.out.println("Incrementer acquired semaphore");
                for (int i = 0; i < numOfIncrements; i++) {
                    SharedArea.value++;
                    System.out.println("New value: " + SharedArea.value);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Incrementer releasing semaphore");
                sem.release();
            }
        }
    }

    private static class Decrementer implements Runnable{

        private final Semaphore sem;
        private final int numOfDecrements;

        Decrementer(Semaphore sem, int numOfDecrements) {
            this.sem = sem;
            this.numOfDecrements = numOfDecrements;
        }

        @Override
        public void run() {
            System.out.println("Decrementer thread launched");
            try {
                sem.acquire();
                System.out.println("Decrementer acquired semaphore");
                for (int i = 0; i < numOfDecrements; i++) {
                    SharedArea.value--;
                    System.out.println("New value: " + SharedArea.value);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Decrementer releasing semaphore");
                sem.release();
            }

        }
    }

}






