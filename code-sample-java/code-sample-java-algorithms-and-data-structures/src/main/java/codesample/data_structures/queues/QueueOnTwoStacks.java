package codesample.data_structures.queues;

import codesample.data_structures.stacks.StackOnArray;

/**
 * @param <T> - Generic item type for this queue
 */
public class QueueOnTwoStacks<T> {

    private final StackOnArray<T> inputStack;
    private final StackOnArray<T> outputStack;

    public QueueOnTwoStacks() {
        inputStack = new StackOnArray<>();
        outputStack = new StackOnArray<>();
    }

    public void enqueue(T item) {
        inputStack.push(item);
    }

    public T dequeue() {
        if (outputStack.isEmpty() && inputStack.isEmpty())
            throw new IllegalArgumentException("Attempting to dequeue from empty queue");

        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.pop();
    }

}
