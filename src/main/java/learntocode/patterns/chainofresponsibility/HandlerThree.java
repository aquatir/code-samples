package learntocode.patterns.chainofresponsibility;

public class HandlerThree extends  Handler {


    @Override
    public void handle() {
        System.out.println("Handler three activated. Stopping chain");
    }

    @Override
    public Handler getNextHandler() {
        return super.getNextHandler();
    }
}