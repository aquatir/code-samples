package learntocode.patterns.chainofresponsibility;

/**
 * Chain of responsibility consists of multiple handlers. Each of them can either
 * 1. handle some function and pass handling further over chain (see {@link HandlerOne} and {@link HandlerTwo} or
 * 2. handle and stop passing further (see {@link HandlerThree}.
 *
 * You may make Handler class and interface with default methods. It this case you actual Handler classes
 * should most likely be created with some builder objects to prevent unfinished chains of responsibility
 */
public abstract class Handler {

    private Handler nextHandler;

    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle() {
    }

    public Handler getNextHandler() {
        return nextHandler;
    }
}