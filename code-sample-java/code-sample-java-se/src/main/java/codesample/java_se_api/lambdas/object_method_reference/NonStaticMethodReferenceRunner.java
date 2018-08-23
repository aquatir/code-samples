package codesample.java_se_api.lambdas.object_method_reference;

import codesample.java_se_api.lambdas.StringOpRunner;

class NonStaticMethodReferenceRunner {
    public static void main(String[] args) {
        String hello = "hello, world!";
        StringOpRunner opRunner = new StringOpRunner();
        NonStaticStringOperations operations = new NonStaticStringOperations();

        /* Take note, that here we cannon use NonStaticStringOperations class instead of operation object
        * because main method required static context. We can actually use this NonStaticStringOperations as
        * first part of method reference. See non_static_class_method_reference for example */
        System.out.println(opRunner.runOp(operations::reverse, hello));
        System.out.println(opRunner.runOp(operations::cutFiveTrailingChars, hello));
    }

}
