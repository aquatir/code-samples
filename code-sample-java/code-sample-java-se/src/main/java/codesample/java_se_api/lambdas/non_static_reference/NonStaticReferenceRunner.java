package codesample.java_se_api.lambdas.non_static_reference;

class NonStaticReferenceRunner {
    public static void main(String[] args) {
        /* there are 1 value which is equal to 5*/
        SomeClassWithNonStaticMethod[] arr = {
                new SomeClassWithNonStaticMethod(3),
                new SomeClassWithNonStaticMethod(5),
                new SomeClassWithNonStaticMethod(10),
                new SomeClassWithNonStaticMethod(4),
                new SomeClassWithNonStaticMethod(4)};

        SomeClassWithNonStaticMethod matchAgainst = new SomeClassWithNonStaticMethod(5);

        /* take note, that the function reference is
        * 1) made to non-static method (it's public in fact)
        * 2) it's type-safe because of generic count method signature
        * */
        int count = Counter.count(arr, SomeClassWithNonStaticMethod::equalValue, matchAgainst);

        System.out.println(count);
    }

}
