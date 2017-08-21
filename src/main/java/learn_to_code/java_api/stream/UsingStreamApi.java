package learn_to_code.java_api.stream;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class UsingStreamApi {
    public static void main(String[] args) {

        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = i / 2;
        }


        /* You can get stream both from collections and from arrays.
        * When using arrays, you can even get primitive type streams by using classes such as
        * IntStream, DoubleStream or LongStream
        *
        * Remember, that you can open stream AND use it immediately, because opening a stream is not finishing operation */
        IntStream arrayStream = Arrays.stream(array);

        /* Here we print all elements from array, using .forEach. forEach takes a consumer which means
        * it uses an element but does not return any value */
        System.out.print("initial array: ");
        IntConsumer printInLine = element -> System.out.printf("%3d ", element);
        arrayStream.forEach(printInLine);
        System.out.println();

        /* Remember that .forEach is finishing operation which mean you can not use the same stream again for anything else
        * and you have to recreate it.
         *
         * Also take note that filter IS NOT finishing operation, that's why we can use forEach() after using filter()
         *
         * The filter takes each element in given stream and either add it to new stream or now. If filter return true for element
         * it will be added to output stream*/
        arrayStream = Arrays.stream(array);

        System.out.print("even elements: ");
        arrayStream.filter((element) -> (element % 2 == 0)).forEach(printInLine);
        System.out.println();

        /* Reduce() turn your stream into a single value. It first takes 2 elements of streams, apply a BiFunction with
        method "CLASS apply(CLASS element1, CLASS element2)" which return a values and then use the result as the first
        element for next iteration. E.g. when summing values 1,2,3,4 reduce will go as following:
        * iteration 1.      0 + 1 = 1
        * iteration 2.      1 + 2 = 3
        * iteration 3.      3 + 3 = 6
        * iteration 4.      6 + 4 = 10.
        *
        * Take note for the first argument and first iteration. The first value is called identity value and it
        * should satisfy equation
        *           apply( x, IDENTITY_VALUE) = x
        *
        * e.g. for summing the identity_value is 0, because x + 0 = x.
        * For multiplying it would be 1, because x * 1 = x, etc
        * */
        arrayStream = Arrays.stream(array);
        System.out.println("sum of elements: " + arrayStream.reduce(0, (a, b) -> (a+b)));

        /* Maps each values from input stream to output stream using provided function. Is NOT finishing operation */
        arrayStream = Arrays.stream(array);
        System.out.print("doubled elements: ");
        arrayStream.map((element) -> element * 2).forEach(printInLine);
        System.out.println();

        /* Collect method will 'collect' elements by rules (function) you provide. This call has 3 arguments
        1. Supplier supplier. Will be called multiple times for each parallel task. This will accumulate elements.
        For normal stream this is called only once, for parallel - multiple times depending on number of parallel tasks
        2. BiConsumer accumulator. It incorporates additional values into result. NOTE: stream string proceeding starts with empty string, so if
        you try to accumulate strings with spaces as I do here, remember that you should remove first space.
        3. BiConsumer combiner. It will combine result of 2 parallel tasks. If stream is sequential, this will be ignored, BUT FOR SOME REASON
        if you provide null instead of BiConsumer you will get NPE ¯\_(ツ)_/¯. NOTE: the behavior of 'nothing being called on sequential stream'
        is NOT GUARANTEED by specification, so you better provide you dummy combiner for sequential streams.
        * */
        String[] names = {"Ivan", "Vasili", "John", "Bob", "Katya"};
        String resultParallel = Arrays.stream(names).parallel().collect(StringBuilder::new,
                (elementsSoFar, newElement) -> elementsSoFar.append(" ").append(newElement),
                (firstParallelTask, secondParallelTask) -> firstParallelTask.append(",").append(secondParallelTask))
                .deleteCharAt(0).toString();

        String resultSequential = Arrays.stream(names).sequential().collect(StringBuilder::new,
                (elementsSoFar, newElement) -> elementsSoFar.append(", ").append(newElement),
                (firstParallelTask, secondParallelTask) -> System.out.println("I am actually getting called!"))
                .delete(0,2).toString();

        System.out.println("result parallel: \"" + resultParallel+ "\"");
        System.out.println("result sequential: \"" + resultSequential + "\"");
    }
}
