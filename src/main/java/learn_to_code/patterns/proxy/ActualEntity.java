package learn_to_code.patterns.proxy;

import java.util.concurrent.TimeUnit;

/**
 * This is an object which is getting created by proxy.
 * To imitate the fact that this object is considerably bigger then proxy
 * we must wait 4 seconds before this object can be initiated
 */
public class ActualEntity implements  EntityInterface {

    public ActualEntity() {
        System.out.println("[ACTUAL]: entity init started");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            System.out.println("Got interrupted while retrieving actual entity");
        }

        System.out.println("[ACTUAL]: entity init finished");
    }

    @Override
    public void someMethod() {
        System.out.println("[ACTUAL]: entity method called");
    }
}
