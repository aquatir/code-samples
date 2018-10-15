package codesample.java_se_api.concurency.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * ConcurrentHashMap example.
 *
 * Concurrent hashmap provides concurrency guarantees on map put/get/getOrDefault and other methods.
 * Here we create 3 threads which put 5000 elements in concurrentHashMap where each element is being put 5000 times.
 *
 * If concurrency is guaranteed for concurrentHashMap after threads are joined we should have 5000 keys in map
 * each mapped to 15000.
 */
public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        var map = new ConcurrentHashMap<Integer, Integer>();

        var putter_1 = new Thread(new PutterTask(map));
        var putter_2 = new Thread(new PutterTask(map));
        var putter_3 = new Thread(new PutterTask(map));

        putter_1.start();
        putter_2.start();
        putter_3.start();

        putter_1.join();
        putter_2.join();
        putter_3.join();

        System.out.println("Number of elements in map: " + map.entrySet().size());
        System.out.println("Elements not equal to 15000: " + map.entrySet()
                .stream()
                .filter(x -> x.getValue() != 15000)
                .collect(Collectors.toUnmodifiableList()));

    }

    private static class PutterTask implements Runnable {

        ConcurrentHashMap<Integer, Integer> map;

        public PutterTask(ConcurrentHashMap<Integer, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            for (var i = 0; i < 5000; i++) {
                for (var j = 0; j < 5000; j++) {
                    map.put(j, map.getOrDefault(j, 0) + 1);
                }
            }
        }
    }
}
