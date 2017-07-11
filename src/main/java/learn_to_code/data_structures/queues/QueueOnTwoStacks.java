package learn_to_code.data_structures.queues;

import learn_to_code.data_structures.stacks.StackOnArray;

/**
 * @param <Item> - Generic item type for this queue
 * @author Pavel Bukhmatov (buhmatov@gmail.com; github.com/aquatir)
 */
public class QueueOnTwoStacks<Item> {

    private StackOnArray<Item> inputStack;
    private StackOnArray<Item> outputStack;

    public QueueOnTwoStacks() {
        inputStack = new StackOnArray<Item>();
        outputStack = new StackOnArray<Item>();
    }

    public void enqueue(Item item) {
        inputStack.push(item);
    }

    public Item dequeue() {
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
