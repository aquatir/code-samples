package learn_to_code.javaSE_api.lambdas.non_static_reference;

public class SomeClassWithNonStaticMethod {
    public int value;

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
