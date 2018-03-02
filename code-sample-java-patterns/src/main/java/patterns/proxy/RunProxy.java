package patterns.proxy;

import java.util.concurrent.TimeUnit;

public class RunProxy {
    public static void main (String[] args) {
        Proxy pr = new Proxy();

        int i =0;
        while (i < 6) {
            pr.someMethod();

            try {
                ++i;
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Got interrupted while retrieving actual entity");
            }
        }
    }
}
