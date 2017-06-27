package learntocode.javaapi.lambdas.generic_method_reference;

public class GenericMethodReferenceRunner {

    public static void main(String[] args) {
        Integer[] array = {1,2,6,3,1,256,5,4,2,3,4,6,1,2,3,2};
        Integer value = 4;

        /* here we have reference to generic method in ArrayOps class */
        int count = ArrayOps.performFunction(ArrayOps::<Integer>countMatching, array, value);
        System.out.println(count);
    }
}
