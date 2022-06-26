package com.codesamples;

import java.util.Random;


// name permitted classes + sealed modifier
abstract sealed class BaseClass permits SubOne, SubTwo {
}

final class SubOne extends BaseClass {
}

final class SubTwo extends BaseClass {
}

enum SomeEnum {
    ONE, TWO, THREE,
}

public class SealedClassesExample {
    public static void main(String[] args) {

        // No need for default branch here because switch is on enum
        // If you add another value to enum,  code will stop compiling due to non-exhaustive clause
        var e = SomeEnum.values()[new Random().nextInt(3)];
        var res = switch (e) {
            case ONE -> "One";
            case TWO -> "Two";
            case THREE -> "Three";
        };
        System.out.println("result of switch on enum is: " + res);

        var one = new SubOne();
        var two = new SubTwo();

        caller(one);
        caller(two);


    }

    public static void caller(BaseClass base) {

        // You also don't need default here, because BaseClass is sealed, and we switch on all the cases.
        // Addint another case to permit will break the compilation which is a good thing
        switch (base) {
            case SubOne subOne -> System.out.println("one");
            case SubTwo subTwo -> System.out.println("two");
        }
    }
}
