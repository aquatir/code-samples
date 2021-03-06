package codesample.java_se_api.lambdas.non_static_reference;

class SomeClassWithNonStaticMethod {
    private int value;

    public SomeClassWithNonStaticMethod(int value) {
        this.value = value;
    }

    private SomeClassWithNonStaticMethod() {}

    public boolean equalValue(SomeClassWithNonStaticMethod other) {
        if (value == other.value)
            return true;
        return false;
    }

    public int getValue() {
        return value;
    }
}
