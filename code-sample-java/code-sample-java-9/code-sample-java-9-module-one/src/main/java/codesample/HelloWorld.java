package codesample;

import com.somepackage.World;

public class HelloWorld {
    public static void main(String[] args) {

        System.out.println("Hello, " + World.name());

        /* Can not use, because package com.other is not exported
        by module codesample.java.nine.module_two
         */

        // EvilWorld.name()
    }
}
