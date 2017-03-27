package learntocode.patterns.chainofresponsibility;

/**
 * Create a chain of 3 handlers.
 * Third handler doesn't make call to setNext nor does it's handle() method pass command to other handlers
 * so it's is ends the chain.
 */
public class RunChainOfResponsibility {
    public static void main(String[] args) {
        HandlerOne handler1 = new HandlerOne();
        HandlerTwo handler2 = new HandlerTwo();
        HandlerThree handler3 = new HandlerThree();

        handler1.setNext(handler2);
        handler2.setNext(handler3);

        handler1.handle();
    }
}
