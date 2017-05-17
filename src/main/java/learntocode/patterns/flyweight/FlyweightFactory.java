package learntocode.patterns.flyweight;

import java.util.WeakHashMap;

/**
 * Flyweight factory will either get an existing object from HashMap
 * or create a new object if it does not exists.
 *
 * See {@link FlyweightObject} and {@link InitParameters} for more info
 */
public class FlyweightFactory {
    private WeakHashMap<InitParameters, FlyweightObject> flyweightMap = new WeakHashMap<>();

    /**
     * Make sure that {@link InitParameters} does respect hashCode/equals contract in order for map to work.
     * @param initParams input parameters. Can actually be anything. You only need to determine a way to
     *                   get hashcode from this parameters
     * @return {@link FlyweightObject}
     */
    public FlyweightObject getFlyweight(InitParameters initParams) {

        if (flyweightMap.containsKey(initParams)) {
            System.out.println("Flyweight object already exists. Returning existing object");
            return flyweightMap.get(initParams);

        } else {
            System.out.println("Flyweight object DOES NOT exist. Creating and returning");
            FlyweightObject obj = new FlyweightObject(initParams);
            flyweightMap.put(initParams, obj);
            return obj;
        }
    }
}
