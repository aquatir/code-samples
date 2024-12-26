package codesample.leetcode.easy;

import java.util.HashMap;
import java.util.List;

/**
 * 1436. Destination City — https://leetcode.com/problems/destination-city/description/
 */
public class _1436_DestinationCity {
    public String destCity(List<List<String>> paths) {
        var fromTo = new HashMap<String, String>();

        for (var path: paths) {
            fromTo.put(path.get(0), path.get(1));
        }

        for (var to: fromTo.values()) {

            // if a 'to' city was never a 'from' city, it's the answer
            if (!fromTo.containsKey(to)) {
                return to;
            }
        }

        // never called, because the answer is guaranteed to be present
        // needed to compile the code
        return "";
    }
}
