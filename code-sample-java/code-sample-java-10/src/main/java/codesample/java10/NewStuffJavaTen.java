package codesample.java10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewStuffJavaTen {
    public static void main(String[] args) {

        /* Type-inference (Note: does not work for non-local variables, array initializers,
        lambdas (when you create lambda. Returning result into var is OK) */
        var message = "Hello, world!";
        System.out.println(message);

        var кукареку = new Кукареку<Кукарек>();

        // Note: This will be array list of Objects
        var empList = new ArrayList<>();

        /* copyOf for collections */
        var list = List.of(1,2,3);
        var copy = List.copyOf(list);

        /* unmodifiable collections on collectors*/
        var set = copy.stream()
                .map(value -> value * 3)
                .collect(Collectors.toUnmodifiableSet());

        /* Collections.unmodifiableList() produces unmodifiable view! Not a copy!*/
        var listOfInt = new ArrayList<Integer>(List.of(1,2,3));
        var copyy = Collections.unmodifiableList(listOfInt);
        System.out.println(copyy);
        listOfInt.add(4);
        System.out.println(copyy);

        /* orElseThrow without arguments throws NoSuchElementException */
        var oneElementList = List.of(5);
        var onlyElement = oneElementList.stream()
                .filter(value -> value > 3)
                .findFirst()
                .orElseThrow();

        /* var is a "special" reserved word */
        var var = Var.var();
        var.var().var().var().var();
    }


    public interface Animal {}
    public interface Cat extends Animal { void meow(); }
    public interface Dog extends Animal { void woof(); }

    void makeSound(Animal animal) {
        var catDog = (Cat & Dog) animal;
        catDog.meow();
        catDog.woof();
    }

    static class Кукареку<T> {

    }

    static class Кукарек {

    }

    public static class Var {
        public static Var var() {
            return new Var();
        }
    }
}
