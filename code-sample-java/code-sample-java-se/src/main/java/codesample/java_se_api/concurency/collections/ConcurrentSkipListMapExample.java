package codesample.java_se_api.concurency.collections;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap example
 *
 * Concurrent skip list map is a sorted-on-keys map
 */
public class ConcurrentSkipListMapExample {
    public static void main(String[] args) throws InterruptedException {
        var map = new ConcurrentSkipListMap<Integer, Integer>();

        var putter_1 = new Thread(new PutterTask(map));
        var putter_2 = new Thread(new PutterTask(map));

        putter_1.start();
        putter_2.start();

        putter_1.join();
        putter_2.join();
    }

    private static class PutterTask implements Runnable {

        ConcurrentSkipListMap<Integer, Integer> map;
        Random rnd = new Random(0);

        public PutterTask(ConcurrentSkipListMap<Integer, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            for (var i = 0; i < 50000; i++) {
                var key = rnd.nextInt(50000);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
    }
}
