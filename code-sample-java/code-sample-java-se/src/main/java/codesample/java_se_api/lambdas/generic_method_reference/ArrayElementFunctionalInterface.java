package codesample.java_se_api.lambdas.generic_method_reference;

/**
 * Functional interface between array of T and element of this type
 * @param <T> type of array and element
 */
interface ArrayElementFunctionalInterface<T> {
    int func(T[] array, T element);
}
