package com.codesample.datastructures;

import java.util.List;

public class MyPriorityQueue<T extends Comparable<T>> {

    private int size;

    public MyPriorityQueue() {
        this.size = 0;
    }

    public MyPriorityQueue(List<T> initElements) {
        this.size = initElements.size();
    }

    void insert(T key) {

    }

//    T delMax() {
//
//    }
//
//    T max() {
//
//    }

    int size() {
        return this.size;
    }

    boolean isEmpty() {
        return size == 0;
    }
}
