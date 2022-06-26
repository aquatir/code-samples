package com.codesamples;


import java.util.Objects;

record Student(String name, int age) {

    public Student {
        // compact constructor: Is does assignments automatically and then run this checks
        Objects.requireNonNull(name);
        if (age <= 0) throw new IllegalArgumentException("Age must not be greater than 1");
    }

}

public class RecordsExample {
    public static void main(String[] args) {
        // public constructor accepting all the fields is automatically generated
        var s1 = new Student("Ivan", 50);

        // toString is also generated
        System.out.println(s1);

        // as well as hash-code and equals
        var s2 = new Student("Ivan", 50);
        System.out.println(s1.hashCode() + ", " +  s2.hashCode());
        System.out.println(s1.equals(s2));

        // and getter
        System.out.println("name " + s1.name() + " age " + s1.age());
    }
}
