package codesample.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. Populating Next Right Pointers in Each Node — https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 */
public class _116_PopulatingNextRightPointersInEachNode {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;


    public Node connect(Node root) {
        // BFS
        // when found the key => return the next
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        Node prev = null;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            prev = null;
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                if (prev != null) {
                    prev.next = node;
                }
                prev = node;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return root;
    }
}
