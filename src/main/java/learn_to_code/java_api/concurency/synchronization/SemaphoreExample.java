package learn_to_code.java_api.concurency.synchronization;

import java.util.concurrent.Semaphore;

/**
 * An example of Semaphore class usage.
 * We create a semaphore with a single permit. Then launch 2 threads. One should increment a shared area value by 5
 * (by 1 on each iteration), another should decrement this value by 5 (by 1 with each iteration).
 *
 * We can see, that 2 threads are starting almost sensationally, but they can not acquire semaphore at the same time
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        int numOfIterations = 5;

        Thread inc = new Thread(new Incrementer(sem, numOfIterations));
        Thread dec = new Thread(new Decrementer(sem, numOfIterations));

        inc.start();
        dec.start();
    }
}

class SharedArea {
    public static int value = 0;
}

class Incrementer implements Runnable {
    Semaphore sem;
    int numOfIncrements;

    public Incrementer(Semaphore sem, int numOfIncrements) {
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
        }
        System.out.println("Incrementer releasing semaphore");
        sem.release();
    }
}

class Decrementer implements Runnable{

    Semaphore sem;
    int numOfDecrements;

    public Decrementer(Semaphore sem, int numOfDecrements) {
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
        }
        System.out.println("Decrementer releasing semaphore");
        sem.release();
    }
}

