package com.codesample.algo;

public class Utils {
    public static void swap(int[] array, int i, int j) {
        var tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
