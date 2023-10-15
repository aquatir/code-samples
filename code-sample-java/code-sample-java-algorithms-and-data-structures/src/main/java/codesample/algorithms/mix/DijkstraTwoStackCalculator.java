package codesample.algorithms.mix;

import codesample.data_structures.stacks.StackOnLinkedList;

/**
 * Two stack calculator for full brackets Strings, e.g. (1+2) is valid, 1+2 is not
 */
class DijkstraTwoStackCalculator {

    private final StackOnLinkedList<Character> ops = new StackOnLinkedList<>();
    private final StackOnLinkedList<Double> values = new StackOnLinkedList<>();

    public Double calculate(String str) {

        boolean inNumber = false;

        char curChar;
        char tmpChar;

        for (int i = 0; i < str.length(); i++) {
            curChar = str.charAt(i);

            /* pushing operations into stack */
            switch (curChar) {
                case '(', ' ' -> inNumber = false;
                case '-' -> {
                    ops.push('-');
                    inNumber = false;
                }
                case '+' -> {
                    ops.push('+');
                    inNumber = false;
                }
                case '*' -> {
                    ops.push('*');
                    inNumber = false;
                }
                case '/' -> {
                    inNumber = false;
                    ops.push('/');
                }
                /*
                 * calculating 2 last values pushed into stack when
                 * we encounter ')' symbol
                 */
                case ')' -> {
                    tmpChar = ops.pop();
                    switch (tmpChar) {
                        case '-' -> values.push((-values.pop() + values.pop()));
                        case '+' -> values.push((values.pop() + values.pop()));
                        case '*' -> values.push((values.pop() * values.pop()));
                        case '/' -> values.push((Math.pow(values.pop(), -1) * values.pop()));
                    }
                    inNumber = false;
                }

                /* Parsing value */
                default -> {
                    if (inNumber) {
                        values.push(values.pop() * 10.0 + Character.getNumericValue(curChar));
                    } else {
                        values.push((double) Character.getNumericValue(curChar));
                        inNumber = true;
                    }
                }
            }
        }

        return values.pop();
    }
}
