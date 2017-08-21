package learn_to_code.javaSE_api.lambdas.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic utility classes for List
 */
public class ListFilter {
    /**
     * Count number of elements in list which pass the filter
     * @param list list of elements to twoElementsFilter
     * @param twoElementsFilter a filtering function
     * @param value value to twoElementsFilter elements against
     * @param <T> type of input list and everything else
     * @return number of elements in list which suffice to given criteria
     */
    public static<T> int count (List<T> list, TwoElementsFilter<T> twoElementsFilter, T value) {
        int count = 0;

        for (T element: list) {
            if (twoElementsFilter.criteria(element, value)) {
                count ++;
            }
        }
        return count;
    }

    /**
     * returns a list of elements which pass the filter
     * @param list list of elements to twoElementsFilter
     * @param twoElementsFilter a filtering function
     * @param value value to twoElementsFilter elements against
     * @param <T> type of input list and everything else
     * @return list of elements which pass the filter
     */
    public static<T> List<T> filteredList(List<T> list, TwoElementsFilter<T> twoElementsFilter, T value) {

        List<T> returnedList = new ArrayList<>();

        for (T element: list) {
            if (twoElementsFilter.criteria(element, value)) {
                returnedList.add(element);
            }
        }
        return returnedList;
    }
}
