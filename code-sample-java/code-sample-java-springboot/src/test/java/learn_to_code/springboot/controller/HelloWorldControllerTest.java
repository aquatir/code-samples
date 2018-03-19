package learn_to_code.springboot.controller;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Spring boot provides features for simplifying spirng-boot apps testing.
 */
@RunWith(SpringRunner.class)    /* If you don't specify this annotation, MOST test annotations will be ignored. */
@SpringBootTest                 /* This will create annotation context for your tests app */
@AutoConfigureMockMvc           /* This allows you to use mocked implementation for mvc, servlet request, servlet
                                 * response, etc */
@Slf4j
public class HelloWorldControllerTest {


    /* injections */
    @Autowired
    HelloWorldController helloWorldController;
    @Autowired
    MockMvc mockMvc;


    /* good test results */
    private final String GET_PROPS_GOOD_RESULT = "ip: /192.168.1.1 enabled: false roles: [USER, ADMIN]";

    /**
     * There are multiple ways to test your MVC mappings. The simple way as shown here:
     * 1. Autrowire contoller (because controller, afterall, is still a @Component, which can be autowired)
     * 2. Call the desired method.
     */
    @Test
    public void testGetProps() {
        String props = helloWorldController.getProps();
        log.info("full props: {}", props);
        Assert.assertTrue(props.contains(GET_PROPS_GOOD_RESULT));
    }

    /**
     * Or use mockMvc for 'real' call testing
     */
    @Test
    @SneakyThrows /* We do not want to cover try-catch. Instead all exceptions will failt the test*/
    public void testGetPropsMvc() {
        mockMvc.perform(get("/props"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(GET_PROPS_GOOD_RESULT)));
    }
}
