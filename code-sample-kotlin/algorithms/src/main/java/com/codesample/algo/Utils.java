package com.codesample.algo;

import java.util.Random;

public class Utils {

    private static Random rnd = new Random();

    public static void swap(int[] array, int i, int j) {
        var tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // see https://en.wikipedia.org/wiki/Fisher–Yates_shuffle shuffle algorithm
    public static void shuffle(int[] array) {
        for (int i = array.length; i >= 1; i--) {
            swap(array, i - 1, rnd.nextInt(i));
        }
    }
}
