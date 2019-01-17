package codesample.java_se_api.stream;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

class UsingStreamApi {
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

        /* Methods such as anyMatch(), allMatch() and noneMatch() allow you to determine if predicate is true for all/some/none
        elements in stream*/
        arrayStream = Arrays.stream(array);
        System.out.println("Element bigger than 10 exists: " + arrayStream.anyMatch(value -> value > 10));
        arrayStream = Arrays.stream(array);
        System.out.println("All elements are bigger that -1: " + arrayStream.allMatch(value -> value > -1));

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
        System.out.println("sum of elements: " + arrayStream.reduce(0, (a, b) -> (a + b)));

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
        if you provide null instead of BiConsumer you will get NPE ¯\_(ツ)_/¯. NOTE: the behavior of 'combiner not being called on sequential stream'
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
                .delete(0, 2).toString();

        System.out.printf("%20s \"%s\"\n", "result parallel:", resultParallel);
        System.out.printf("%20s \"%s\"\n", "result sequential:", resultSequential);

        /* Also note, that collect method can be used to get COLLECTIONS from your streams using
        * predefined static methods in Collectors class such as toSet() toList(), toMap(), toConcurrentMap()*/
        Set<String> namesAsSet = Arrays.stream(names).collect(Collectors.toSet());
        System.out.printf("%20s", "Elements in set 1: \"");
        namesAsSet.forEach((e) -> System.out.print(e + " "));
        System.out.print("\"\n");

        /* Instead of calling toSet you can actually do something like this: (don't do it...)*/
        namesAsSet = Arrays.stream(names).collect(HashSet::new,
                HashSet::add,
                HashSet::addAll);

        System.out.printf("%20s", "Elements in set 2: \"");
        namesAsSet.stream().forEach((e) -> System.out.print(e + " "));
        System.out.print("\"\n");

        /* Or like this: (obviously don't do it) */
        namesAsSet = Arrays.stream(names).collect((Supplier<HashSet>) HashSet::new,
                HashSet::add,
                AbstractCollection::addAll);

        System.out.printf("%20s", "Elements in set 3: \"");
        namesAsSet.stream().forEach((e) -> System.out.print(e + " "));
        System.out.print("\"\n");

        // You can use different collectors from Collect. There are many of them. Here is an example of groupby collector.
        String value = "abcddfseca";
        Map<Character, Long> collection = value.chars()
                .mapToObj(charAsInt -> (char) charAsInt)
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting())
                );

        System.out.println(collection);

    }
}
