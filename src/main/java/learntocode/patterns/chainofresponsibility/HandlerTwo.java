package learntocode.patterns.chainofresponsibility;

public class HandlerTwo extends  Handler {

    public void setNext(Handler nextHandler) {
        super.setNext(nextHandler);
    }

    @Override
    public void handle() {
        System.out.println("Handler two activated. Handling further on chain");
        getNextHandler().handle();
    }

    @Override
    public Handler getNextHandler() {
        return super.getNextHandler();
    }
}
