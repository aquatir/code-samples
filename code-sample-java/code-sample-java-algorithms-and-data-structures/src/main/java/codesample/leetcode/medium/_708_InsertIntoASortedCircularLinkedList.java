package codesample.leetcode.medium;

/**
 * 708. Insert into a Sorted Circular Linked List — https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
 */
public class _708_InsertIntoASortedCircularLinkedList {

    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };


    static class Solution {

        // approach 1: find the minimum and shit-code to cover every single possible case
        public Node insert(Node head, int insertVal) {
            // head.val > insertVal: insert behind.
            // head.val < insertVal: insert in front.
            // we must find a node that has val < insertVal closes to insertVal as possible and insert the new value there.

            if (head == null) { // 0 elements
                head = new Node(insertVal);
                head.next = head;
                return head;
            }

            if (head.next == head) { // 1 element
                var inserted = new Node(insertVal);
                head.next = inserted;
                inserted.next = head;
                return head;
            }

            // find the smallest node, from it find the insertion point
            // also track the largest node with a separate search
            var smallestNode = head;
            var largestNode = head;
            var cur = smallestNode.next;

            while (cur != smallestNode) {
                if (cur.val < smallestNode.val) {
                    smallestNode = cur;
                }
                cur = cur.next;
            }

            cur = head.next;
            while (cur != largestNode) {
                if (cur.val > largestNode.val) {
                    largestNode = cur;
                }
                cur = cur.next;
            }

            // at this point both smallestNode & cur are at the elements with smallest value.

            // special case: if insertVal is the new lowest => must insert it before the smallest aka at the end of the list
            if (insertVal <= smallestNode.val) {
                while (cur.next.val != smallestNode.val) {
                    cur = cur.next;
                }
                var newNode = new Node(insertVal);
                newNode.next = cur.next;
                cur.next = newNode;
                return head;
            }

            // special case: if insertVal is greater than the greatest => insert after the greatest
            if (insertVal >= largestNode.val) {
                // skip the same elements
                while (largestNode.val == largestNode.next.val) {
                    largestNode = largestNode.next;
                }
                var newNode = new Node(insertVal);
                newNode.next = largestNode.next;
                largestNode.next = newNode;
                return head;
            }

            // now iterate until the next is not bigger
            while (cur.next.val <= insertVal) {
                cur = cur.next;
            }



            // at this point cur.next is bigger than insertVal
            var newNode = new Node(insertVal);
            newNode.next = cur.next;
            cur.next = newNode;

            return head;
        }
    }

    public static void main(String[] args) {
        var s = new Solution();

        var head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(3);
        head.next.next.next = head;
        var res = s.insert(head, 2);

        System.out.println(res.val);
    }
}
