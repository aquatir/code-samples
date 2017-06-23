package learntocode.javaapi.lambdas;

/**
 * A class used to call string operations.
 */
public class StringOpRunner {
    public String runOp(StringFunc func, String str) {
        return func.call(str);
    }
}
