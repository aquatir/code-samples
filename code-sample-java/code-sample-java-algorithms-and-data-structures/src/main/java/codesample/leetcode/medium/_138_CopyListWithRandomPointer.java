package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. Copy List with Random Pointer — https://leetcode.com/problems/copy-list-with-random-pointer/description/
 */
public class _138_CopyListWithRandomPointer {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    Map<Node, Node> visitedHash = new HashMap<>();

    public Node copyRandomList(Node head) {

        // short-circuit
        if (head == null) {
            return null;
        }

        // compare elements by object hash — works in this case
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }

        var node = new Node(head.val);
        this.visitedHash.put(head, node);

        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);

        return node;
    }

}
