package learn_to_code.java_api.lambdas.generic_method_reference;

/**
 * Functional interface between array of T and element of this type
 * @param <T> type of array and element
 */
public interface ArrayElementFunctionalInterface<T> {
    int func (T[] array, T element);
}
