package learn_to_code.javaSE_api.lambdas.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FilterExampleRunner {
    public static void main(String[] args) {

        /* initialization */
        int capacity = 10;
        List<Integer> list = new ArrayList<>(capacity);
        Random rnd = new Random();
        int compareAgainstValue = rnd.nextInt(capacity);
        TwoElementsFilter<Integer> greaterFilter = (v1, v2) -> (v1 > v2);

        for (int i = 0; i < capacity; i++) {
            list.add(rnd.nextInt(capacity));
        }
        list.sort((value1, value2) -> {
            if (value1 > value2)
                return 1;
            else if (value1 < value2)
                return -1;
            return 0;
        });

        /* Starting here */
        System.out.println("Initial list: ");
        list.forEach(element -> System.out.print(element + " "));
        System.out.println("\n");

        List<Integer> filteredList = ListFilter.filteredList(list, greaterFilter, compareAgainstValue);
        int count = ListFilter.count(list, greaterFilter, compareAgainstValue);

        System.out.println("Filtered out everything greater then " + compareAgainstValue);
        filteredList.forEach(element -> System.out.print(element + " "));
        System.out.println("\n");
        System.out.println("Number of filtered elements: " + count);


    }
}
