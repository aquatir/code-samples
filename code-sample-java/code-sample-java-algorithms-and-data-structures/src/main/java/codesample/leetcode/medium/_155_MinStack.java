package codesample.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155. Min Stack — https://leetcode.com/problems/min-stack/description/
 */
public class _155_MinStack {

    // approach 1: PQ. does not satisfy O(1) condition in a task, but passes tests
    // approach 2: Store value in stack + min together. Works with O(1)
    // approach 3: Store value in stack + keep min value stack. Works with O(1) but more efficient
    // approach 4: Same as Approach 3, but keep track of how many of the same mins were encountered

    // approach 1: PQ. does not satisfy O(1) condition in a task, but passes tests
//    class MinStack {
//
//        Deque<Integer> deq;
//
//        // technically works, but will be O(log (n)) while we need O(1)
//        PriorityQueue<Integer> pq;
//
//        public MinStack() {
//            this.deq = new ArrayDeque<>();
//            this.pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
//        }
//
//        public void push(int val) {
//            deq.push(val);
//            pq.offer(val);
//        }
//
//        public void pop() {
//            var popped = deq.pop();
//            pq.remove(popped);
//        }
//
//        public int top() {
//            return deq.peekFirst();
//        }
//
//        public int getMin() {
//            return pq.peek();
//        }
//    }

    // approach 2: Store value in stack + min together. Works with O(1)
//    class MinStack {
//
//        private class ValAndMin {
//            private int value;
//            private int min;
//
//            public ValAndMin(int value, int min) {
//                this.value = value;
//                this.min = min;
//            }
//        }
//
//        Deque<ValAndMin> deq;
//
//        public MinStack() {
//            this.deq = new ArrayDeque<>();
//        }
//
//        public void push(int val) {
//            var minToPush = val;
//            if (!deq.isEmpty()) {
//                minToPush = Math.min(this.getMin(), val);
//            }
//
//            this.deq.push(new ValAndMin(val, minToPush));
//        }
//
//        public void pop() {
//            deq.pop();
//        }
//
//        public int top() {
//            return deq.peekFirst().value;
//        }
//
//        public int getMin() {
//            return deq.peekFirst().min;
//        }
//    }


    // approach 3: Store value in stack + keep min value stack. Works with O(1) but more efficient
//    class MinStack {
//
//        Deque<Integer> deq;
//        Deque<Integer> minStack;
//
//        public MinStack() {
//            this.deq = new ArrayDeque<>();
//            this.minStack = new ArrayDeque<>();
//        }
//
//        public void push(int val) {
//            deq.push(val);
//
//            if (minStack.isEmpty()) {
//                minStack.push(val);
//            } else {
//                // have to push the same value more than once so it won't be removed instantly
//                if (val <= getMin()) {
//                    minStack.push(val);
//                }
//            }
//        }
//
//        public void pop() {
//            var v = deq.pop();
//
//            if (v == getMin()) {
//                minStack.pop();
//            }
//        }
//
//        public int top() {
//            return deq.peekFirst();
//        }
//
//        public int getMin() {
//            return minStack.peekFirst();
//        }
//    }

    // approach 4: Same as Approach 3, but keep track of how many of the same mins were encountered
    class MinStack {

        Deque<Integer> deq;
        Deque<ValAndMin> minStack;

        private class ValAndMin {
            private int val;
            private int count;

            private ValAndMin(int val, int count) {
                this.val = val;
                this.count = count;
            }
        }

        public MinStack() {
            this.deq = new ArrayDeque<>();
            this.minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            deq.push(val);

            if (minStack.isEmpty()) {
                minStack.push(new ValAndMin(val, 1));
            } else {
                // either push new min, or increment the count of mins
                var min = getMinInternal();
                if (val < min.val) {
                    minStack.push(new ValAndMin(val, 1));
                } else if (val == min.val) {
                    var cur = minStack.pop();
                    cur.count++;
                    minStack.push(cur);
                }
            }
        }

        public void pop() {
            var v = deq.pop();

            var min = getMinInternal();
            if (v == min.val) {

                if (min.count == 1) {
                    minStack.pop();
                } else {
                    var cur = minStack.pop();
                    cur.count--;
                    minStack.push(cur);
                }
            }
        }

        public int top() {
            return deq.peekFirst();
        }

        public int getMin() {
            return minStack.peekFirst().val;
        }

        private ValAndMin getMinInternal() {
            return minStack.peekFirst();
        }
    }

}
