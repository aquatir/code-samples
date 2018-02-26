package learn_to_code.springboot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring does not care if you {@code @Controller} is defined as controller or {@code RestController}. This annotation
 * is there simply to make it easier to developers to understand what are they dealing with
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
