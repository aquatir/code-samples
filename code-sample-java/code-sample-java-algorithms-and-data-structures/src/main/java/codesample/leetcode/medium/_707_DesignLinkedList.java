package codesample.leetcode.medium;

/**
 * 707. Design Linked List — https://leetcode.com/problems/design-linked-list/
 *
 * TODO: try it with Sentinel node, see solution https://leetcode.com/problems/design-linked-list/solutions/398730/official-solution/
 * + bi-directional list
 */
public class _707_DesignLinkedList {

    // first impl without sentinel node
//    static class MyLinkedList {
//
//        class Node {
//            public int val;
//            public Node next;
//
//            public Node(int val) {
//                this.val = val;
//            }
//        }
//
//        private Node head;
//        private int size;
//
//        public MyLinkedList() {
//            this.size = 0;
//            this.head = null;
//        }
//
//        public int get(int index) {
//            if (index < 0 || index >= size || size == 0) {
//                return -1;
//            }
//
//            // if index == 0 => skip none
//            // if index == 1 => skip only 1 element
//            // if index == 2 => skip only 2 elements, return third
//            var curNode = this.head;
//            for (int i = 0; i < index; i++) {
//                curNode = curNode.next;
//            }
//
//            return curNode.val;
//        }
//
//        public void addAtHead(int val) {
//            var newHead = new Node(val);
//            if (head == null) {
//                this.head = newHead;
//            } else {
//                newHead.next = this.head;
//                this.head = newHead;
//            }
//            size++;
//        }
//
//        public void addAtTail(int val) {
//            if (head == null) {
//                addAtHead(val);
//                return;
//            } else {
//                var cur = this.head;
//                while (cur.next != null) {
//                    cur = cur.next;
//                }
//
//                var newNode = new Node(val);
//                cur.next = newNode;
//                size++;
//            }
//        }
//
//        public void addAtIndex(int index, int val) {
//            if (index == 0) {
//                addAtHead(val);
//                return;
//            }
//            if (index == size) {
//                addAtTail(val);
//                return;
//            }
//            if (index < 0 || index > size || size == 0) {
//                return;
//            }
//
//            // if index = 1 => prev = index[0], cur = index[1]
//            Node prev = null;
//            Node cur = this.head;
//            for (int i = 0; i < index; i++) {
//                prev = cur;
//                cur = cur.next;
//            }
//            Node newNode = new Node(val);
//            prev.next = newNode;
//            newNode.next = cur;
//            size++;
//        }
//
//        public void deleteAtIndex(int index) {
//            if (index < 0 || index >= size || size == 0) {
//                return;
//            }
//            if (index == 0) {
//                if (size == 1) {
//                    head = null;
//                    size = 0;
//                    return;
//                } else {
//                    size--;
//                    head = head.next;
//                    return;
//                }
//            }
//
//            // if index = 1 => prev = index[0], cur = index[1]
//            Node prev = null;
//            Node cur = this.head;
//            for (int i = 0; i < index; i++) {
//                prev = cur;
//                cur = cur.next;
//            }
//            Node afterCur = cur.next;
//            prev.next = afterCur;
//            cur = null;
//            size--;
//        }
//    }

    // second impl: with head as sentinel node => makes all code easier
    static class MyLinkedList {

        class Node {
            public int val;
            public Node next;

            public Node(int val) {
                this.val = val;
            }
        }

        private Node head;
        private int size;

        public MyLinkedList() {
            this.size = 0;
            this.head = new Node(0); // sentinel node
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }

            // if index == 0 => skip none
            // if index == 1 => skip only 1 element
            // if index == 2 => skip only 2 elements, return third
            var curNode = this.head;
            for (int i = 0; i < index + 1; i++) {
                curNode = curNode.next;
            }

            return curNode.val;
        }

        public void addAtHead(int val) {
            this.addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            this.addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) {
                return;
            }

            Node pred = this.head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            var toAdd = new Node(val);
            toAdd.next = pred.next;
            pred.next = toAdd;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }

            var pred = this.head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            pred.next = pred.next.next;
            size--;
        }
    }

    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */

    public static void main(String[] args) {
        var ll = new MyLinkedList();
        /*
        ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
[                   [], [7],            [2],     [1],        [3,0],       [2],           [6],          [4],         [4],    [4],       [5,0],       [6]]
         */
//        ll.addAtHead(7);
//        ll.addAtHead(2);
//        ll.addAtHead(1);
//        ll.addAtIndex(3, 0);
//        ll.deleteAtIndex(2);
//        ll.addAtHead(6);
//        ll.addAtTail(4);
//        System.out.println(ll.get(4));
//        ll.addAtHead(4);
//        ll.addAtIndex(5,0);
//        ll.addAtHead(6);

        /*
        ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
        [[],            [1],        [3],        [1,2],          [1],        [0],    [0]]
         */
//        ll.addAtHead(1);
//        ll.addAtTail(3);
//        ll.addAtIndex(1,2);
//        System.out.println(ll.get(1));
//        ll.deleteAtIndex(0);
//        ll.get(0);
        /*
        ["MyLinkedList","addAtIndex","addAtIndex","addAtIndex","get"]
        [[],            [0,10],         [0,20],     [1,30],     [0]]
         */
        ll.addAtIndex(0, 10);
        ll.addAtIndex(0, 20);
        ll.addAtIndex(1, 30);
        System.out.println(ll.get(0));

        System.out.println("kekw");
    }
}
