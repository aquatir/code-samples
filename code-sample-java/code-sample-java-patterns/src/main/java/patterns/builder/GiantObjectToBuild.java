package patterns.builder;

/**
 * You typically use builder to build large object with a big number of parameters
 * some of which may or may not be optional.
 *
 * Builder provides a mechanism to create an immutable (optional) object in such a way that no thread can get parameters
 * of such object until the construction is done. Builder is using non-final fields which are then translated
 * to GiantObject when call to build is called. This call creates a new object although it's not required.
 *
 * Take note that you can chain calls to value-setting methods value2(), value3() as both of them return builder object
 */
public class GiantObjectToBuild {

    public static class Builder {

        /* required */
        private int value1 = 0;

        /* optional */
        private int value2 = 0;
        private int value3 = 0;

        public Builder(int value) {
            value1 = value;
        }

        public Builder value2(int value) { value2 = value; return this; }
        public Builder value3(int value) { value3 = value; return this; }

        public GiantObjectToBuild build() {
            return new GiantObjectToBuild(this);
        }

    }

    private GiantObjectToBuild(Builder builder) {
        /* required */
        int value1 = builder.value1;
        /* optional */
        int value2 = builder.value2;
        int value3 = builder.value3;
    }
}
