package learntocode.patterns.singleton;

/**
 * The fastest way is to double check if instance was already created
 */
public class SingletonFast {

    private static SingletonFast singletonInstance;

    private SingletonFast() {
        /* do something */
    }


    public static SingletonFast getInstance() {
        /* imagine multiple thread go to this line */
        if (singletonInstance == null)

            /* now only one of them inter synchronized area and create singleton*/
            synchronized (SingletonFast.class) {

                /* Once singleton is created, any other thread attempting to create it would fail this on this
                * if block*/
                if (singletonInstance == null) {
                    singletonInstance = new SingletonFast();
                }
            }

        return singletonInstance;
    }
}
