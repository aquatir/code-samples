package codesample.data_structures.stacks;

/**
 * Stack implementation with array.
 * stack size get doubled when max array size is reached.
 * stack size get halfed when 2/3 of array is empty.
 * This leeds to better performance on resize operations and also
 * works around thrashing problem
 */
public class StackOnArray<T> {

    private T[] array;
    private int head; // next index for added element

    public StackOnArray() {
        array = (T[]) new Object[16];
        head = 0;
    }

    public StackOnArray(int initialSize) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Initial stack size should be greater then 0");
        }
        array = (T[]) new Object[initialSize];
        head = 0;
    }

    /**
     * @return true if array is empty and false otherwize
     */
    public boolean isEmpty() {
        return (head == 0);
    }

    /**
     * Sets new size for array in stack
     * @param newSize new array size
     */
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, head);
        array = newArray;
    }

    /**
     * @return Returns actuall number of element in stack.
     */
    public int getSize() {
        return head;
    }

    /**
     * @return length of stack as maximum array size before resizing needed
     */
    public int getLength() {
        return array.length;
    }

    /**
     * push element into stack
     * @param item new element to put into stack
     */
    public void push(T item) {
        if (head == array.length) {
            // double the size
            resize( array.length << 2 );
        }

        if (isEmpty()) {
            array[0] = item;
        } else {
            array[head] = item;
        }
        ++head;
    }

    /**
     * pops element from stack removing it from array
     * @return popped element
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Attempting to pop from empty stack");
        }

        T element = array[head - 1];
        head--;

        /* If 2/3 of array is empty, resize array and lower it's size in half*/
        if (head < array.length / 3) {
            resize(array.length / 2 + 1);
        }
        return element;
    }
}
