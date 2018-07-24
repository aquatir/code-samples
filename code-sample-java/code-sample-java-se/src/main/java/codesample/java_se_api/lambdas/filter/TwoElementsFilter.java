package codesample.java_se_api.lambdas.filter;

/**
 * Functional interface for any logical decision about one element being better in some way then another
 * @param <T> parameter type
 */
public interface TwoElementsFilter<T> {
    boolean criteria(T v1, T v2);
}
