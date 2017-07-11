package learn_to_code.java_api.lambdas;

/**
 * Basic functional interface for custom string functions.
 * <br>
 * NOTE: You should never create functional interface with a simple signature like this one
 * because Java already provide it in {@link java.util.function.UnaryOperator} interface.
 * <br>
 * Check other interfaces in this package to see standard interfaces Java provides.
 */
public interface StringFunc {
    String call(String str);
}