package codesample.java10;

import java.util.function.Function;
import java.util.function.Supplier;

public class Stuff {

    private static SomeConditions cnd = new SomeConditions(
            (value) -> value > 5,
            () -> "Hello, world");

    public static void main(String[] args) {
        int value = 10;
        boolean otherFlag = true;

        System.out.println(cnd.doStuff(value, otherFlag));
    }

    public static class SomeConditions {

        private Function<Integer, Boolean> conditionOne;
        private Supplier<String> successSupplier;

        public SomeConditions(
                Function<Integer, Boolean> conditionOne,
                Supplier<String> successSupplier) {
            this.conditionOne = conditionOne;
            this.successSupplier = successSupplier;
        }

        public String doStuff(int value, boolean otherFlag) {
            if (otherFlag) {
                if (this.conditionOne.apply(value)) {
                    return successSupplier.get();
                }
            }
            return "kek";
        }
    }
}
