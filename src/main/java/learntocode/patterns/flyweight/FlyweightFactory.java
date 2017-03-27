package learntocode.patterns.flyweight;

import java.util.WeakHashMap;

/**
 * Flyweight factory will either get an existing object from HashMap
 * or create a new object if it does not exists.
 *
 * See {@link FlyweightObject} and {@link InitParameters} for more info
 */
public class FlyweightFactory {
    private WeakHashMap<Integer, FlyweightObject> flyweightMap = new WeakHashMap<Integer, FlyweightObject>();

    /**
     * Make sure that {@link InitParameters} does return correct hashCode if value which this hashcode
     * is based on is getting changed
     * @param initParams input parameters. Can actually be anything. You only need to determine a way to
     *                   get hashcode from this parameters
     * @return {@link FlyweightObject}
     */
    public FlyweightObject getFlyweight(InitParameters initParams) {

        if (flyweightMap.containsKey(initParams.hashCode())) {
            System.out.println("Flyweight object already exists. Returning existing object");
            return flyweightMap.get(initParams);

        } else {
            System.out.println("Flyweight object DOES NOT exist. Creating and returning");
            FlyweightObject obj = new FlyweightObject(initParams);
            flyweightMap.put(initParams.hashCode(), obj);
            return obj;
        }
    }
}
