package codesample.java_se_api.lambdas.static_class_method_references;


/**
 * Take note that methods in this class have the save signature as {@link codesample.java_se_api.lambdas.StringFunc} functional interface.
 * This is required in order to make :: notation references
 */
public class StaticStringOperations {
    static String reverse (String str) {
        StringBuffer sb = new StringBuffer(str);
        return sb.reverse().toString();
    }

    public static String cutFiveTrailingChars(String str) {
        return str.substring(0, str.length() - 5);
    }

}
