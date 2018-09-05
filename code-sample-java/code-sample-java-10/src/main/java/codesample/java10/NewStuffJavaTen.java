package codesample.java10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewStuffJavaTen {
    public static void main(String[] args) {

        /* Type-inference (Note: does not work for non-local variables, array initializers,
        lambdas (when you create lambda. Returning result into var is OK) */
        var message = "Hello, world!";
        System.out.println(message);

        // Note: This will be array list of Objects
        var empList = new ArrayList<>();

        /* copyOf for collections */
        var list = List.of(1,2,3);
        var copy = List.copyOf(list);

        /* unmodifiable collections on collectors*/
        var set = copy.stream()
                .map(value -> value * 3)
                .collect(Collectors.toUnmodifiableSet());

        /* orElseThrow without arguments throws NoSuchElementException */
        var oneElementList = List.of(5);
        var onlyElement = oneElementList.stream()
                .filter(value -> value > 3)
                .findFirst()
                .orElseThrow();
    }
}
