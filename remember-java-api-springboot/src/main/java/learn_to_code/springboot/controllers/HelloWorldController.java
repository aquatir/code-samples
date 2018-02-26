package learn_to_code.springboot.controllers;

import learn_to_code.springboot.props.FooProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring does not care if you {@code @Controller} is defined as controller or {@code RestController}. This annotation
 * is there simply to make it easier to developers to understand what are they dealing with
 */
@RestController
public class HelloWorldController {

    @Autowired
    private FooProperties props;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/props")
    @ResponseBody
    public String getProps() {
        return "ip: " + props.getIp().toString() + " enabled: " + props.isEnabled() + " roles: " + props.getRoles();
    }
}
