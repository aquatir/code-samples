package codesample.java_se_api.lambdas.static_class_method_references;

import codesample.java_se_api.lambdas.StringOpRunner;

public class StaticMethodReferenceRunner {
    public static void main(String[] args) {
        String hello = "hello, world!";
        StringOpRunner opRunner = new StringOpRunner();

        System.out.println(opRunner.runOp(StaticStringOperations::reverse, hello));
        System.out.println(opRunner.runOp(StaticStringOperations::cutFiveTrailingChars, hello));
    }

}
