/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codesample.data_structures.stacks;


public class StackOnLinkedList<T> {
    private Node head;
    private int size;
    
    private class Node {
        private final T value;
        private Node next;
        
        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }
    
    boolean isEmpty() {
        return size == 0;
    }
    
    public void push(T value) {
        Node newHead = new Node(value);
        newHead.next = head;
        head = newHead;
        ++size;
    }
    
    public T pop() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException ("Attempting to pop from empty stack");
        }
        
        T value = head.value;
        
        Node newHead = head.next;
        head.next = null;
        head = newHead;
        
        --size;
        return value;
    }
}
