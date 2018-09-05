package codesample.java_se_api.lambdas.generic_method_reference;

class ArrayOps {
    /**
     * Return number of elements in array which are equal to given value
     * Method with same signature as {@link codesample.java_se_api.lambdas.generic_method_reference.ArrayElementFunctionalInterface}
     * @param array any array
     * @param value value which may or may not be in array
     * @param <T> type of array and value
     * @return number of elements in array which are equal to given value
     */
    static <T> int countMatching(T[] array, T value) {
        int count = 0;

        for (T anArray : array) {
            if (anArray.equals(value))
                count++;
        }

        return count;
    }

    /**
     * Method with an only purpose to execute any somepackage method defined in this class
     * @param func functional interface which will be executed
     * @param array array with same parameter as func interface
     * @param value value as in func interface
     * @param <T> type of array, value and functional interface
     * @return integer value depending on functional interface executed
     */
    static <T> int performFunction(ArrayElementFunctionalInterface<T> func, T[] array, T value) {
        return func.func(array, value);
    }
}
