import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloBossTests {

    @Test
    void testStuff() {
        var stuff = new HelloBoss();
        "kek".indexOf("f", 1);
        Assertions.assertEquals("kek", stuff.someMethod());
        Assertions.assertEquals(null, stuff.someNullMethod());
        Assertions.assertEquals(true, stuff.someTrueMethod());
    }

}
