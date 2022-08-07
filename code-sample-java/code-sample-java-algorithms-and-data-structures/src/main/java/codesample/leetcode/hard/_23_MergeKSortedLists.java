package codesample.leetcode.hard;

import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists — https://leetcode.com/problems/merge-k-sorted-lists/
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
public class _23_MergeKSortedLists {


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
        }
    }


    public ListNode mergeKLists(ListNode[] lists) {
//        // approach 1: PriorityQueue of pairs of index + value
//        //   on each add we move the ptr in correct list to next
//
//        ListNode head = null;
//        ListNode current = null;
//
//        var pq = new PriorityQueue<Pair<Integer, Integer>>(
//            (one, two) -> Integer.compare(one.getValue(), two.getValue())
//        );
//
//        // add all nodes initially
//        for (int i = 0; i < lists.length; i++) {
//            var cur = lists[i];
//            while (cur != null) {
//                pq.offer(new Pair<>(i, cur.val));
//                cur = cur.next;
//            }
//        }
//
//        while (!pq.isEmpty()) {
//            var entry = pq.poll();
//            var newNode = new ListNode(entry.getValue());
//            if (head == null) {
//                head = newNode;
//                current = head;
//            } else {
//                current.next = newNode;
//                current = current.next;
//            }
//        }
//
//        return head;

        // approach 2: MergeSort: PQ of only the first element. Pick one, advance the list from where this element was picked
        //
        ListNode head = null;
        ListNode current = null;

        var pq = new PriorityQueue<Pair<Integer, Integer>>(
            (one, two) -> Integer.compare(one.getValue(), two.getValue())
        );

        // add only the first nodes
        for (int i = 0; i < lists.length; i++) {
            var cur = lists[i];
            if (cur != null) {
                pq.offer(new Pair<>(i, cur.val));
            }
        }

        while (!pq.isEmpty()) {
            var entry = pq.poll();
            var newNode = new ListNode(entry.getValue());
            if (head == null) {
                head = newNode;
                current = head;
            } else {
                current.next = newNode;
                current = current.next;
            }

            // advance the list from which newNode was taken
            var listIndex = entry.getKey();
            lists[listIndex] = lists[listIndex].next;
            if (lists[listIndex] != null) {
                pq.offer(new Pair<>(listIndex, lists[listIndex].val));
            }
        }

        return head;
    }
}
