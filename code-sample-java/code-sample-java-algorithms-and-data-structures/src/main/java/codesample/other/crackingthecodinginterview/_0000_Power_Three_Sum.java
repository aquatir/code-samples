package codesample.other.crackingthecodinginterview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// Find all positive integer solutions under 1.000 to a^3 + b^3 = c^3 + d^3
public class _0000_Power_Three_Sum {
    public long numberOfSolutions() {
        // Types of solutions:
        // 1. equality predicates. a=c; b=d OR a=d; b=c; Solutions to this will include double-equals, e..g a=b=c=d
        // 2. true equals. a != c; a != d; b != c; b != d;

        // Approach:
        // 1. Check all possible solutions. 2 for-loop until 1000 => 1kk => small enough to brute force
        // if same solutions is hit more than once => possibly a second case
        //  -- could be a repeat of 1 case. e.g. 5,2 = 2,5 and 5,2 = 5,2 => diff cases ?
        //  -- can count only unique solutions, by utilizing a set + creating an equality data-structure

        // If I know that (a,b) is a possible sum. What do I know about c and d
        //   - for (a,b), both (c,d) and (d,c) are valid solutions, where c == a; d == b or c = b; d == a
        //   - there might be other (c,d) not equal to either a or b, that are solutions too.
        //      -- how to count them?
        // What If there was a map [result] -> Set<(a,b)>
        //  The total number of solutions would be then:
        //      -- for each entry => add 2 solutions (c,d)
        //      -- for each len(Set<(a,b)>) - 1 (aka all other solutions that are NOT equal) => add 2 more (c,d) ?
        //      ---- ???
        // example:
        // res 16776487 from pairs: (58,255);(183,220);(220,183);(255,58). Let's count manually:
        // 1. a=58,b=255;c=58,d=255
        // 2. a=58,b=255;c=255,d=58
        // 3. a=58,b=255;c=220,d=183
        // 4. a=58,b=255;c=183,d=220
        // totalling 16 solutions

        // (58,255) alone is 4 uniques

        // Maybe we can count "sorted" solutions instead? Not (10, 15) and (15, 10) as separate, but a single (10, 15) as a unique solutions
        // this way, when we encounter a unique solutions, we multiply the number of solutions by 4 (due to combinatorics), unless a=b, then only by 2
        // when a single problem has more than 1 unique solutions, we put 4 in a power of number of uniques (does it work every time? Probably,
        // unless there are cases where (a=b; c!=d and a^3+b^3 = c^3+d^3)
        // so (58, 255), (183, 220)...
        //  -- (58, 255) => 4 uniques
        //  -- (183, 220) => 4 uniques
        //  -- 4*4 = 16 uniques

        // Let's see what the data gives first

        var solutions = new HashMap<Long, Set<Solution>>();
        int maxPos = 1000;

        for (int i = 0; i <= maxPos; i++) {
            for (int j = 0; j <= maxPos; j++) {
                long res = (long) Math.pow(i, 3) + (int) Math.pow(j, 3);
                var sol = new Solution(Math.min(i, j), Math.max(i, j));

                if (solutions.get(res) == null) {
                    var set = new HashSet<Solution>();
                    set.add(sol);
                    solutions.put(res, set);
                } else {
                    solutions.get(res).add(sol);
                }
            }
        }

        for (var e: solutions.entrySet()) {
            var str = e.getValue().stream().map(it -> "(" + it.a() + "," + it.b() + ")").collect(Collectors.joining(";"));
            System.out.println("res " + e.getKey() + " from pairs: " + str);
        }

        long totalNumberOfResults = 0;
        for (var e: solutions.entrySet()) {
            var sols = e.getValue();

            if (sols.size() == 1) { // either one combo or equal
                var solution = sols.stream().toList().get(0);
                if (solution.a() == solution.b()) {
                    totalNumberOfResults += 1; // just one (a,a = a,a)
                } else {
                    totalNumberOfResults += 4; // (a,b=c,d), (b,c=c,d), (a,b=d,c), (b,a=c,d);
                }
            } else {
//                // this assumes that (a=b; c!=d and a^3+b^3 = c^3+d^3) can not be true
//                // which seems to be true for number <= 1000;
//                var len = sols.size();
//                totalNumberOfResults += (long) Math.pow(4, len);

                // what if it can be true
                // instead of Math.pow(4,len), we will
                //  1. Math.pow(4,len), where len == unique 2 different values solutions
                //  2. multiple this result by 2 + extra 1?
                //      (a,b)(c,d) is two unique, + (f,f). How many combos are there?
                // -- (a,b,a,b)
                // -- (a,b,b,a)
                // -- (a,b,c,d)
                // -- (a,b,d,c) // repeat 4 times with (a,b replaces with (b,c; (c,d; (d,c

                // how does (f,f) extend the result?
                // (a,b,f,f)
                // (f,f,a,b)  repeat for (b,c; (c,d; (d,c, so 8 extra results in total.
                // There could only be a single (f,f). Two is impossible, because that would've been the same result
                // so with (f,f) we add an extra num_of_unique_res * 4 ?
                // check case 1 (a,b); (f,f).
                //  Math.pow(4,len), where len == unique 2 different values solutions => gives Math.pow(4,1) => 4
                //   multiple this result by 2 + extra 1? = 4 * 2 + 1 = 9
                // (a,b, a,b)
                // (a,b, b,a)
                // (b,a, a,b),
                // (b,a, b,a)
                // (f,f, a,b)
                // (f,f, b,a)
                // (a,b, f,f)
                // (a,b, f,f)
                // (f,f, f,f)
                // case 2: (a,b); (c,d), (f,f,)
                // (a,b, a,b)
                // (a,b, b,a)
                // (a,b, c,d)
                // (a,b, d,c) // + 12 more for (b,c; (c,d; (d,c. What about (f,f) ?
                // all above goes to just (a,b, f,f), then also
                // (b,a, f, f),
                // (c,d, f, f),
                // (d,c, f, f) + repeat with ff on another side, total + 8 solutions.

                // Resulting formula seems to be: add 4 * num_of_unique + extra 1
                // if one unique: Math.pow(4,1) + 4 + 1 = 9
                // if two unique: Math.pow(4,2) + 8 + 1 = 25

                var len = sols.size();
                var duplicateExists = false;
                for (var s: sols) {
                    if (s.a() == s.b()) {
                        duplicateExists = true;
                        break;
                    }
                }

                if (!duplicateExists) {
                    totalNumberOfResults += (long) Math.pow(4, len);
                } else {
                    var unique = (long) Math.pow(4, len - 1);
                    var extras = 4 * (len-1) + 1;
                    totalNumberOfResults += unique + extras;
                }
            }
        }

        return totalNumberOfResults;
    }

    record Solution (int a, int b) {

    }


    public static void main(String[] args) {
        var s = new _0000_Power_Three_Sum();
        System.out.println(s.numberOfSolutions());
    }
}
