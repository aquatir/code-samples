package learntocode.patterns.chainofresponsibility;

public class HandlerOne extends  Handler {

    public void setNext(Handler nextHandler) {
        super.setNext(nextHandler);
    }

    @Override
    public void handle() {
        System.out.println("Handler one activated. Handling further on chain");
        getNextHandler().handle();
    }

    @Override
    public Handler getNextHandler() {
        return super.getNextHandler();
    }
}
