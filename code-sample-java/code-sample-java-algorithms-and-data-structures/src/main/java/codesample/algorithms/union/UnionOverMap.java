package codesample.algorithms.union;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Union implementation backed by array
 */
public abstract class UnionOverMap<T extends Comparable<T>> implements Union<T> {

    // Maps values to indexes
    private Map<T, Integer> map;
    private Integer nextSetIndex;


}
