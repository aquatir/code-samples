package learn_to_code.patterns.proxy;

/**
 * Proxy holds {@link ActualEntity} inside. When actual entity method is called
 * proxy starts to initialize ActualEntity and also provide some default behavior while init is in process.
 * This is useful when initialization might take considerable time like downloading/uploading huge document, etc.
 *
 * NOTE: This is not the one-and-only proxy implementation. Actually there are about 10 different patterns
 * with the same idea under the name "proxy"
 */
public class Proxy implements  EntityInterface {

    private ActualEntity actualEntity;
    boolean running = false;

    @Override
    public void someMethod() {
        if (actualEntity != null) {
            actualEntity.someMethod();
        }
        else {
            if (!running) {
                running = true;
                Thread retrieveActualEntity = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /* this will take 4 second is our example */
                        actualEntity = new ActualEntity();
                    }
                });
                retrieveActualEntity.start();
            }

            System.out.println("[PROXY]: Calling proxy method while actual is getting retrieved");
        }
    }
}
