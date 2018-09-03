package codesample.java_se_api.lambdas.object_method_reference;


/**
 * Take note that methods in this class have the save signature as {@link codesample.java_se_api.lambdas.StringFunc} functional interface.
 * This is required in order to make :: notation references
 */
class NonStaticStringOperations {
    public String reverse (String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public String cutFiveTrailingChars(String str) {
        return str.substring(0, str.length() - 5);
    }

}
