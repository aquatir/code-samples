package learn_to_code.java_se_api.lambdas.non_static_reference;


/**
 * This class has a single method which would count the number of elements which match all element in array
 * which a given element valueTomMatchAgainst by using provided functional interface.
 *
 * The method should not necessarily be static, but you should provide an adequate type-safe signature
 * so that non-static reference to methods would work correctly
 */
public class Counter {
    public static <T> int count(T[] array, SomeFunctionalInterface<T> func, T valueToMatchAgainst) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (func.func(array[i], valueToMatchAgainst))
                counter++;
        }

        return counter;
    }


}
