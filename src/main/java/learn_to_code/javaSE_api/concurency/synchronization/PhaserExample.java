package learn_to_code.javaSE_api.concurency.synchronization;

import java.util.concurrent.Phaser;

/**
 * An example of Phaser usage.
 *
 * We will create 2 threads each with 3 phases. This thread will only proceed with their work
 * when the main thread calls arriveAdnAwaitAdvanced() method. This will happen 3 times. After that
 * both threads and main thread will be deregistered.
 */
public class PhaserExample {
    public static void main(String[] args) throws InterruptedException {

        Phaser phaser = new Phaser(1);

        Thread member1 = new Thread(new phaserMember(phaser, "Thread_1"));
        Thread member2 = new Thread(new phaserMember(phaser, "Thread_2"));

        member1.start();
        member2.start();

        System.out.println("Starting phase 1 from main thread");
        phaser.arriveAndAwaitAdvance();
        Thread.sleep(1000);

        System.out.println("Starting phase 2 from main thread");
        phaser.arriveAndAwaitAdvance();
        Thread.sleep(1000);

        System.out.println("Starting phase 3 from main thread");
        phaser.arriveAndAwaitAdvance();
        Thread.sleep(1000);

        System.out.println("Deregistering phaser");
        phaser.arriveAndAwaitAdvance();
    }
}

class phaserMember implements Runnable {

    private Phaser phaser;
    private String name;

    public phaserMember(Phaser phaser, String threadName) {
        this.phaser = phaser;
        this.name = threadName;
        this.phaser.register();
    }

    @Override
    public void run() {

        phaser.arriveAndAwaitAdvance();
        System.out.println(name + " arrived to first phase ");

        phaser.arriveAndAwaitAdvance();
        System.out.println(name + " arrived to second phase ");

        phaser.arriveAndAwaitAdvance();
        System.out.println(name + " arrived to third phase ");

        phaser.arriveAndDeregister();
        System.out.println(name + " arrived and deregistered ");
    }
}
