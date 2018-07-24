/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codesample.data_structures.stacks;

/**
 *
 * @author pavel
 */
public class StackOnLinkedList<GenericItemType> {
    Node head;
    int size;
    
    private class Node {
        private GenericItemType value;
        private Node next;
        
        public Node(GenericItemType value) {
            this.value = value;
            this.next = null;
        }
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void push(GenericItemType value) {
        Node newHead = new Node(value);
        newHead.next = head;
        head = newHead;
        ++size;
    }
    
    public GenericItemType pop() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException ("Attempting to pop from empty stack");
        }
        
        GenericItemType value = head.value;
        
        Node newHead = head.next;
        head.next = null;
        head = newHead;
        
        --size;
        return value;
    }
}
