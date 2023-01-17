package codesample.leetcode.medium;

/**
 * 430. Flatten a Multilevel Doubly Linked List — https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/
 */
public class _430_FlattenAMultilevelDoublyLinkedList {

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val, Node prev, Node next, Node child) {
            this.val = val;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }
    }

    static class Solution {
        public Node flatten(Node head) {
            if (head == null) return head;
            var sentinel = new Node(0, null, head, null);

            flattenAllBetween(sentinel, head);

            // remove sentinel head
            sentinel.next.prev = null;
            return sentinel.next;
        }

        public Node flattenAllBetween(Node prev, Node cur) {

            // this happens when
            // 1) we are at the end of list
            // 2) there is no child
            if (cur == null) {
                return prev;
            }

            // in case we are handling a child node
            cur.prev = prev;
            prev.next = cur;

            var tempNext = cur.next;

            var tail = flattenAllBetween(cur, cur.child);
            cur.child = null;

            return flattenAllBetween(tail, tempNext);
        }
    }

    public static void main(String[] args) {
        var s = new _430_FlattenAMultilevelDoublyLinkedList.Solution();

        /**
         * 1---2---3---4---5---6--NULL
         *          |
         *          7---8---9---10--NULL
         *              |
         *              11--12--NULL*
         */

        var node1 = new Node(1, null, null, null);
        var node2 = new Node(2, null, null, null);
        var node3 = new Node(3, null, null, null);
        var node4 = new Node(4, null, null, null);
        var node5 = new Node(5, null, null, null);
        var node6 = new Node(6, null, null, null);
        var node7 = new Node(7, null, null, null);
        var node8 = new Node(8, null, null, null);
        var node9 = new Node(9, null, null, null);
        var node10 = new Node(10, null, null, null);
        var node11 = new Node(11, null, null, null);
        var node12 = new Node(12, null, null, null);

        node1.next = node2;

        node2.prev = node1;
        node2.next = node3;

        node3.prev = node2;
        node3.next = node4;

        node4.prev = node3;
        node4.next = node5;

        node5.prev = node4;
        node5.next = node6;

        node6.prev = node5;
        node6.next = null;

        node3.child = node7;
        node7.prev = node3;
        node7.next = node8;

        node8.prev = node7;
        node8.next = node9;

        node9.next = node10;
        node9.prev = node8;

        node10.next = null;
        node10.prev = node9;

        node8.child = node11;
        node11.prev = node8;
        node11.next = node12;

        node12.next = null;
        node12.prev = node11;

        var head = s.flatten(node1);
        // expected: 1 -> 2 -> 3 -> 7 -> 8 -> 11 -> 12 -> 9 -> 10 -> 4 -> 5 -> 6 ->
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }
}
