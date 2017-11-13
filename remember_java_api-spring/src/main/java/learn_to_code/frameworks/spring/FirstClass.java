package learn_to_code.frameworks.spring;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FirstClass {

    private static final Logger log = LogManager.getLogger(FirstClass.class);

    public static void main(String[] args) {

        FirstClass clazz = new FirstClass();
        log.info("Hello, world!");
    }
}
