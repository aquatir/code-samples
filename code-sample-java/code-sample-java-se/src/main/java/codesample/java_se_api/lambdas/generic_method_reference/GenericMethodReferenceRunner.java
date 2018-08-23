package codesample.java_se_api.lambdas.generic_method_reference;

class GenericMethodReferenceRunner {

    public static void main(String[] args) {
        Integer[] array = {1,2,6,3,1,256,5,4,2,3,4,6,1,2,3,2};
        Integer value = 4;

        /* here we have reference to generic method in ArrayOps class
        * Also note: we do not actually need to specify generic type here, as java
        * is smart enough to resolve type by itself. This is done for demonstration
        * purpose only */
        int count = ArrayOps.performFunction(ArrayOps::<Integer>countMatching, array, value);

    }
}
