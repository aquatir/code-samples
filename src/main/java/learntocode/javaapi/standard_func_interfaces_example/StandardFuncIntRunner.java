package learntocode.javaapi.standard_func_interfaces_example;

import java.util.function.*;

/**
 * This class provide simple examples of {@link java.util.function} package functional interfaces usage
 */
public class StandardFuncIntRunner {

    public static void main(String[] args) {

        UnaryOperator<String> reverter = str -> new StringBuilder(str).reverse().toString(); // reverts a string
        BinaryOperator<String> concatinator = (str1, str2) -> str1 + str2; // concatinates 2 strings
        Function<String, Integer> lengthCounter = str -> str.length(); // gets length of string

        Consumer<String> strPrinter = str -> System.out.println("str = " + str); // prints a string in fancy way
        Supplier<String> helloWorldSupplier = () -> "hello, world"; // gives you specified string (in a pretty dumb way)
        Predicate<String> isHelloWorld = str -> str.equals("hello, world"); // checks if given string is "hello, world

        String hello = "Hello";
        String world = ", World!";
        printStr(reverter.apply(hello)); // will print "olleH"
        printStr(concatinator.apply(hello, world)); // will print "hello, World!"
        printStr(lengthCounter.apply(hello)); // will print 5
        strPrinter.accept(hello); // will print "str = Hello

        String helloWorld = helloWorldSupplier.get(); // will get you "hello, world" string
        printStr(helloWorld);

        printStr(isHelloWorld.test(hello)); // will print false
        printStr(isHelloWorld.test(helloWorld));
    }

    public static <T> void printStr(T str) {
        System.out.println(str.toString());
    }


}
