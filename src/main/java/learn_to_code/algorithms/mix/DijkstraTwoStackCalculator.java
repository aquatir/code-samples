package learn_to_code.algorithms.mix;

import learn_to_code.data_structures.stacks.StackOnLinkedList;

/**
 * @author pavel
 */
public class DijkstraTwoStackCalculator {

    StackOnLinkedList<Character> ops = new StackOnLinkedList<Character>();
    StackOnLinkedList<Double> values = new StackOnLinkedList<Double>();

    public Double calculate(String str) {

        boolean inNumber = false;

        char curChar;
        char tmpChar;

        for (int i = 0; i < str.length(); i++) {
            curChar = str.charAt(i);

            /* pushing operations into stack */
            switch (curChar) {
                case '(':
                case ' ':
                    inNumber = false;
                    break;
                case '-':
                    ops.push('-');
                    inNumber = false;
                    break;
                case '+':
                    ops.push('+');
                    inNumber = false;
                    break;
                case '*':
                    ops.push('*');
                    inNumber = false;
                    break;
                case '/':
                    inNumber = false;
                    ops.push('/');
                    break;
                /* 
                * calculating 2 last values pushed into stack when
                * we encounter ')' symbol 
                */
                case ')':
                    tmpChar = ops.pop();
                    switch (tmpChar) {
                        case '-':
                            values.push((-values.pop() + values.pop()));
                            break;
                        case '+':
                            values.push((values.pop() + values.pop()));
                            break;
                        case '*':
                            values.push((values.pop() * values.pop()));
                            break;
                        case '/':
                            values.push((Math.pow(values.pop(), -1) * values.pop()));
                            break;

                        default:
                            break;
                    }

                    inNumber = false;
                    break;

                /* Parsing value */
                default:
                    if (inNumber) {
                        values.push(values.pop() * 10.0 + (double) Character.getNumericValue(curChar));
                    } else {
                        values.push((double) Character.getNumericValue(curChar));
                        inNumber = true;
                    }
                    break;
            }
        }

        return values.pop();
    }
}
