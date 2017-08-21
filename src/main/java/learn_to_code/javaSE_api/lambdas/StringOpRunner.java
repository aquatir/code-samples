package learn_to_code.javaSE_api.lambdas;

/**
 * A class used to call string operations.
 */
public class StringOpRunner {
    public String runOp(StringFunc func, String str) {
        return func.call(str);
    }
}
